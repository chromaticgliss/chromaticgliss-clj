(ns chromaticgliss.handler-test
  (:use clojure.test
        chromaticgliss.test-core
        ring.mock.request
        chromaticgliss.handler)
  (:require [cheshire.core :as json]
            [chromaticgliss.models.users :as users]
            [chromaticgliss.models.posts :as posts]
            [chromaticgliss.auth :as auth]))

(def ^{:dynamic true} *session-id* nil)

(defn with-session [t]
  (let [user (users/create {:name "Some admin"
                        :email "theadmin@example.com"
                        :password "sup3rs3cr3t"
                        :level :chromaticgliss.auth/admin})
        session-id (auth/make-token! (:id user))]
    (with-bindings {#'*session-id* session-id}
      (t))
    (users/delete-user user)))

(use-fixtures :each with-rollback)
(use-fixtures :once with-session)

(defn with-auth-header [req]
  (header req "Authorization" (str "Token " *session-id*)))

(deftest main-routes
  (testing "list users"
    (let [response (app (with-auth-header (request :get "/api/users")))]
      (is (= (:status response) 200))
      (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8"))))

  (testing "posts endpoint"
    (let [response (app (with-auth-header (request :get "/api/posts")))]
      (is (= (:status response) 200))
      (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8"))))

  (testing "not-found route"
    (let [response (app (request :get "/bogus-route"))]
      (is (= (:status response) 404)))))

(deftest creating-user
  (testing "POST /api/users"
    (let [user-count (users/count-users)
          response (app (-> (request :post "/api/users")
                            with-auth-header
                            (body (json/generate-string {:name "Joe Test"
                                                         :email "joe@example.com"
                                                         :password "s3cr3t"}))
                            (content-type "application/json")
                            (header "Accept" "application/json")))]
      (is (= (:status response) 201))
      (is (substring? "/api/users/" (get-in response [:headers "Location"])))
      (is (= (inc user-count) (users/count-users))))))

(deftest retrieve-user-stuff
  (let [user (users/create {:name "John Doe" :email "j.doe@mytest.com" :password "s3cr3t"})
        initial-count (users/count-users)]
    (testing "GET /api/users"
      (doseq [i (range 4)]
        (users/create {:name "Person" :email (str "person" i "@example.com") :password "s3cr3t"}))
      (let [response (app (with-auth-header (request :get "/api/users")))
            resp-data (json/parse-string (:body response))]
        (is (= (:status response 200)))
        (is (substring? "person3@example.com" (:body response)))
        (is (= (+ initial-count 4) (count (get resp-data "results" []))))
        (is (= (+ initial-count 4) (get resp-data "count" [])))))
    (testing "GET /api/users/:id"
      (let [response (app (with-auth-header (request :get (str "/api/users/" (:id user)))))]
        (is (= (dissoc (json/parse-string (:body response) true) :created_at :updated_at :level) (dissoc user :created_at :updated_at :level)))))))

(deftest deleting-user
  (let [user (users/create {:name "John Doe" :email "j.doe@mytest.com" :password "s3cr3t"})]
    (testing "DELETE /api/users/:id"
      (let [response (app (with-auth-header (request :delete (str "/api/users/" (:id user)))))]
        (is (= (:status response) 204))
        (is (= "/api/users" (get-in response [:headers "Location"])))
        (is (nil? (users/find-by-id (:id user))))))))

(deftest creating-post
  (testing "POST /api/posts"
    (let [post-count (posts/count-posts)
          user (users/create {:name "Mojo Jojo" :email "mojo@jojo.com" :password "mojojojo"})
          response (app (-> (request :post "/api/posts")
                            with-auth-header
                            (body (str "{\"title\":\"Yoyoyo\", \"slug\":\"yoyoyo\",\"user_id\":" (:id user) ", \"body\":\"Heyoo, is make blog posts, ohhhh\"}"))
                            (content-type "application/json")
                            (header "Accept" "application/json")))]
      (is (= (:status response) 201))
      (is (substring? "/posts/" (get-in response [:headers "Location"])))
      (is (= (inc post-count) (posts/count-posts))))))

(deftest retrieving-post
  (let [post-count (posts/count-posts)
        user (users/create {:name "Jingly Joe" :email "jingly.joe@website.net" :password "s3cr3t"})
        post (posts/create {:title "I Am Jingly" :slug "i-am-jingly" :user_id (user :id) :body "My name is Jingly Joe. I am pretty Jingly. I am also pretty Joe-y." })]
    (testing "GET /api/posts"
      (doseq [i (range 4)]
        (posts/create {:title (str "Post " i) :slug (str "post-" i) :user_id (user :id) :body (str "This is post " i ".")}))
      (let [response (app (request :get "/api/posts"))
            resp-data (json/parse-string (:body response))]
        (is (= (:status response 200)))
        (is (substring? "Post 3" (:body response)))
        (is (= (+ 5 post-count) (count (get resp-data "results" []))))
        (is (= (+ 5 post-count) (get resp-data "count" [])))))
    (testing "GET /api/posts/id/:id"
      (let [response (app (request :get (str "/api/posts/id/" (:id post))))]
        (is (= (dissoc (json/parse-string (:body response) true) :created_at :updated_at) (dissoc post :created_at :updated_at)))))
    (testing "GET /api/posts/:slug"
      (let [response (app (request :get (str "/api/posts/" (:slug post))))]
        (is (= (dissoc (json/parse-string (:body response) true) :created_at :updated_at) (dissoc post :created_at :updated_at)))))))

(deftest deleting-post
  (let [user (users/create {:name "Brobo Cop" :email "brobocop@bro.net" :password "brotect"})
        post (posts/create {:title "A Bad Post" :slug "a-bad-post" :user_id (user :id) :body "This post is terrible."})]
    (testing "DELETE /api/posts/:slug"
      (let [response (app (with-auth-header (request :delete (str "/api/posts/" (:slug post)))))]
        (is (= (:status response) 204))
        (is (= "/api/posts" (get-in response [:headers "Location"])))
        (is (nil? (posts/find-by-id (:id post))))))
    (testing "DELETE /api/posts/id/:id"
      (let [response (app (with-auth-header (request :delete (str "/api/posts/id/" (:id post)))))]
        (is (= (:status response) 204))
        (is (= "/api/posts" (get-in response [:headers "Location"])))
        (is (nil? (posts/find-by-id (:id post))))))))

;; FIXME add test for post update and user update

