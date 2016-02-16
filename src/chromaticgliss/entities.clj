(ns chromaticgliss.entities
  (:use korma.core
        chromaticgliss.db))

(declare users lists products)

(defentity users
  (pk :id)
  (table :users)
  (has-many lists)
  (entity-fields :id :name :email :level :created_at :updated_at))

(defentity lists
  (pk :id)
  (table :lists)
  (belongs-to users {:fk :user_id})
  (many-to-many products :lists_products {:lfk :list_id
                                          :rfk :product_id})
  (entity-fields :id :title :user_id :created_at :updated_at))

(defentity products
  (pk :id)
  (table :products)
  (many-to-many lists :lists_products {:lfk :product_id
                                       :rfk :list_id})
  (entity-fields :id :title :description :created_at))

(defentity posts
  (pk :id)
  (table :posts)
  (belongs-to users {:fk :user_id})
  (entity-fields :id :title :slug :body :user_id :created_at :updated_at))

(defentity auth-tokens
  (pk :id)
  (table :auth_tokens)
  (belongs-to users {:fk :user_id}))
