(ns chromaticgliss.models.posts
  (:use korma.core)
  (:require [chromaticgliss.entities :as e]))

(defn find-all []
  "Find all posts"
  (some-> (select e/posts)
          (with e/users)
          (fields :id :title :slug :body :user_id :created_at :updated_at :users.name :users.email)))


(defn find-all-by-user [user]
  "Find all posts by a given user"
  (some-> (select e/posts)
          (with e/users)
          (fields :id :title :slug :body :user_id :created_at :updated_at :users.name :users.email)
          (where {:user_id (user :id)})
          select))

(defn find-by [field value]
  "Find all posts where the given field matches a given value"
  (some-> (select e/posts)
          (with e/users)
          (fields :id :title :slug :body :user_id :created_at :updated_at :users.name :users.email)
          (where {field value})
          (limit 1)
          select
          first))

(defn find-by-id [id]
  "Find post with given id"
  (find-by :id id))

(defn find-by-slug [slug]
  "Find post with given slug"
  (find-by :slug slug))

(defn create [post]
  "Create new post. Passed map of params."
  (-> (insert* e/posts)
      (values post)
      insert))

(defn update-post [post]
  "Update post. Map of params must supply id of post to edit."
  (update e/posts
    (set-fields (dissoc post :id))
    (where {:id (read-string (post :id))})))

(defn update-post-by-slug [post]
  "Update post. Map of params must supply slug of post to edit."
  (update e/posts
    (set-fields (dissoc post :slug))
    (where {:slug (post :slug)})))

(defn update-post-by-slug [post]
  (update e/posts
    (set-fields (dissoc post :slug))
    (where {:slug (post :slug)})))

(defn delete-post [post]
  "Delete post. Map of params must supply id of post to edit."
  (delete e/posts
    (where {:id (post :id)})))

(defn delete-post-by-slug [post]
  "Delete post. Map of params must supply slug of post to edit."
  (delete e/posts
    (where {:slug (post :slug)})))

(defn count-posts []
  "Count all posts."
  (let [agg (select :posts
                    (aggregate (count :*) :cnt))]
    (get (first agg) :cnt)))


