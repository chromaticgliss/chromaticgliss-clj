(ns chromaticgliss.entities
  (:use korma.core
        chromaticgliss.db))

(declare users posts auth-tokens)

(defentity users
  (pk :id)
  (table :users)
  (has-many posts {:fk :user_id})
  (has-many auth-tokens {:fk :user_id})
  (entity-fields :id :name :email :level :created_at :updated_at :active))

(defentity auth-tokens
  (pk :id)
  (table :auth_tokens)
  (belongs-to users {:fk :user_id}))

(defentity posts
  (pk :id)
  (table :posts)
  (belongs-to users {:fk :user_id})
  (entity-fields :id :title :slug :body :user_id :created_at :updated_at))









