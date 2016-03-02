(ns chromaticgliss.models.posts
  (:use korma.core)
  (:require [chromaticgliss.entities :as e]))

(defn find-all []
  (select e/posts))

(defn find-all-by-user [user]
  (some-> (select* e/posts)
          (where {:user_id (user :id)})
          select))

(defn find-by [field value]
  (some-> (select* e/posts)
          (where {field value})
          (limit 1)
          select
          first))

(defn find-by-id [id]
  (find-by :id id))

(defn find-by-slug [slug]
  (find-by :slug slug))

(defn create [post]
  (-> (insert* e/posts)
      (values post)
      insert))

(defn update-post [post]
  (update e/posts
    (set-fields (dissoc post :id))
    (where {:id (post :id)})))

(defn update-post-by-slug [post]
  (update e/posts
    (set-fields (dissoc post :slug))
    (where {:slug (post :slug)})))

(defn delete-post [post]
  (delete e/posts
    (where {:id (post :id)})))

(defn delete-post-by-slug [post]
  (delete e/posts
    (where {:slug (post :slug)})))

(defn count-posts []
  (let [agg (select :posts
                    (aggregate (count :*) :cnt))]
    (get (first agg) :cnt)))


