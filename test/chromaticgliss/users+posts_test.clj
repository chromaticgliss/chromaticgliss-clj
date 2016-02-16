(ns chromaticgliss.users+posts-test
  (:use clojure.test
        chromaticgliss.test-core)
  (:require [chromaticgliss.models.users :as users]
            [chromaticgliss.models.posts :as posts]))

(use-fixtures :each with-rollback)

(deftest user-post-interactions
  (testing "Find user for given post"
    (let [user (users/create {:name "Some Guy" :email "just.some@guy.com" :password "whaaat"})
          my-post (posts/create {:title "Post for some guy"
                                 :body "Some guy has a lot to say"
                                 :slug "some-guys-post"
                                 :user_id (user :id)})]
      (is (= (users/find-by-id (user :id)) (users/for-post my-post)))))
  (testing "Find all posts by a user"
      (let [user (users/create {:name "Dude" :email "duuuude@maaaan.com" :password "dood"})]
      (doseq [i (range 10)]
          (posts/create {:title "test post"
                         :body (str "test post" i ".")
                         :slug (str "test-post-" i)
                         :user_id (user :id)}))
      (is (every? #(= (% :user_id) (user :id)) (posts/find-all-by-user user))))))


(deftest cascading-operations
  (testing "Deleting user removes associated posts"
      (let [steve (users/create {:name "Steve"
                                  :email "steve@wonder.com"
                                  :password "yooo"})
          a-post (posts/create {:title "Steves post"
                                  :body "This post is ephemeral"
                                  :slug "steves-post"
                                  :user_id (steve :id)})]
      (users/delete-user steve)
      (is (nil? (posts/find-by-id (a-post :id)))))))
