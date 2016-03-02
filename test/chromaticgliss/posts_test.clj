(ns chromaticgliss.posts-test
  (:use clojure.test
        chromaticgliss.test-core)
  (:require [chromaticgliss.models.posts :as posts]
            [chromaticgliss.models.users :as users]
            [chromaticgliss.entities :as e]
            [korma.core :as sql]))

(use-fixtures :each with-rollback)

(deftest create-read-posts
  (testing "Create post"
    (let [orig-post-count (posts/count-posts)
          post (posts/create {:title "How To Clojure"
                              :body "Just type a lot of parentheses"
                              :slug "how-to-clojure"})]
      (is (= (inc orig-post-count) (posts/count-posts)))))
  (testing "Retrieve post"
    (let [post (posts/create {:title "Diddly Doo Bop"
                              :body "Shoopity doowop. Boo bop."
                              :slug "ding-dong-bing"})
          found-post (posts/find-by-id (post :id))]
      (is (= "Diddly Doo Bop" (found-post :title)))
      (is (= "ding-dong-bing" (found-post :slug)))))
  (testing "Find by slug"
    (let [post (posts/create {:title "Goobers"
                              :body "Goobers are for doofers"
                              :slug "goobers"})
          found-post (posts/find-by-slug (post :slug))]
      (is (= "Goobers" (found-post :title))))))

(deftest multiple-post-operaions
  (testing "Find all posts"
    (let [user (users/create {:name "Dude" :email "duuuude@maaaan.com" :password "dood"})]
        (doseq [i (range 10)]
          (posts/create {:title "Test post"
                         :body (str "Test post " i ".")
                         :slug (str "test-post-" i)
                         :user_id (user :id)}))
        (is (= 10 (count (posts/find-all)))))))

(deftest update-post
  (testing "Modifies existing post"
    (let [user (users/create {:name "Dude" :email "duuuude@maaaan.com" :password "dood"})
          post-orig (posts/create {:title "OG Post"
                                   :body "This is the original post."
                                   :slug "og-post"
                                   :user_id (user :id)})]
      (posts/update-post (assoc post-orig :body "GET TO DA CHOPPA"))
      (is (= "GET TO DA CHOPPA" (:body (posts/find-by-id (post-orig :id))))))))

(deftest delete-post
  (testing "Decreases post count"
    (let [user (users/create {:name "Bro" :email "brooooo@duuuuude.com" :password "dood"})
          post (posts/create {:title "Bad Post"
                              :body "This is some terrible content"
                              :slug "bad-post"
                              :user_id (user :id)})
          post-count (posts/count-posts)]
      (posts/delete-post post)
      (is (= (dec post-count) (posts/count-posts)))))
  (testing "Deleted correct post"
    (let [user (users/create {:name "Maaaaan" :email "maaaaaan@brooooo.com" :password "maan"})
          post-del (posts/create {:title "Bad Post"
                              :body "This is some terrible content"
                              :slug "bad-post"
                              :user_id (user :id)})
          post-keep (posts/create {:title "Good Post"
                              :body "This is some terrible content"
                              :slug "good-post"
                              :user_id (user :id)})]
      (posts/delete-post post-del)
      (is (= post-keep (posts/find-by-id (post-keep :id))))
      (is (nil? (posts/find-by-id (post-del :id))))))
  (testing "Decreases post count (by slug)"
    (let [user (users/create {:name "Guuuurl" :email "guuuuuuurl@chiiiiicaaa.com" :password "guurl"})
          post (posts/create {:title "Worst Post"
                              :body "This is some terrible content"
                              :slug "worst-post"
                              :user_id (user :id)})
          post-count (posts/count-posts)]
      (posts/delete-post-by-slug post)
      (is (= (dec post-count) (posts/count-posts)))))
  (testing "Deleted correct post (by slug)"
    (let [user (users/create {:name "Oooold Maaaaan" :email "oldmaaaaaan@brooooo.com" :password "oldmaan"})
          post-del (posts/create {:title "Bad Old Post"
                              :body "This is some terrible content"
                              :slug "bad-old-post"
                              :user_id (user :id)})
          post-keep (posts/create {:title "Good Old Post"
                              :body "This is some terrible content"
                              :slug "good-old-post"
                              :user_id (user :id)})]
      (posts/delete-post-by-slug post-del)
      (is (= post-keep (posts/find-by-id (post-keep :id))))
      (is (nil? (posts/find-by-id (post-del :id)))))))
