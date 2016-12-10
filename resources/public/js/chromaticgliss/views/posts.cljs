(ns chromaticgliss.views.posts
  (:require [reagent.core :as r]
            [clojure.string :refer [join]]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.shared :refer [input-field markdown-editor login-modal]]))

(def post-state
  (r/atom {:posts []}))

(defn handle-error [_]
  (.log js/console "Error on request"))

(defn restore-post [post-cur]
  (GET (str "/api/posts/id/" (@post-cur :id)) {:format :json
                                               :response-format :json
                                               :keywords? true
                                               :params @post-cur
                                               :handler #(swap! post-cur assoc :editing? false)
                                               :error-handler handle-error}))

(defn save-post [post-cur]
  (POST (str "/api/posts/id/" (@post-cur :id))  {:format :json
                                                 :response-format :json
                                                 :keywords? true
                                                 :params  (select-keys @post-cur [:title :slug :body])
                                                 :handler #(swap! post-cur assoc :editing? false)
                                                 :error-handler #(restore-post post-cur)}))

(defn confirm-delete [post-cur] )

(defn post-editor [post-cur]
  [:form {:id (str "post-editor-" (@post-cur :id)) :class "post-editor"}
   [input-field post-cur "text" "Title: " :title]
   [input-field post-cur "text" "Slug: " :slug]
   [markdown-editor post-cur "Body: " :body]
   [:input {:type "hidden"  :value (@post-cur :id)}]
   [:button {:type "button" :on-click #(save-post post-cur)} "Save"]
   [:button {:type "button" :on-click #(restore-post post-cur)} "Cancel"]])

(defn post-component [post-cur]
  [:div {:id (str "post-"(@post-cur :id)) :class "post"}
   [:h1 (@post-cur :title)]
   [:div {:class "body"} (@post-cur :body)]
   [:div {:class "author"} (str "author: " (@post-cur :user_id))]
   [:div {:class "date"} (@post-cur :created_at)]
   [:button {:type "button" :on-click #(if (contains? (@shared-state :user) :session)
                                         (swap! post-cur assoc :editing? true)
                                         (swap! shared-state show-login? true))} "Edit"]
   [:button {:type "button" :on-click #(confirm-delete post-cur)} "Delete"]])

(defn post-lister [posts]
  [:div {:id "all_posts"}
    (for [[i post] (map vector (iterate inc 0) @posts)]
      (if (post :editing?)
        ^{:key (str (post :id) "-ed")} [post-editor (r/cursor posts [i])]
        ^{:key (post :id)} [post-component (r/cursor posts [i])]))])

(defn ^:export render-posts []
  (GET "/api/posts" {:format :json
                     :response-format :json
                     :keywords? true
                     :handler #(swap! post-state assoc :posts (% :results))})
  (r/render [post-lister (r/cursor post-state [:posts])] (.getElementById js/document "app")))
