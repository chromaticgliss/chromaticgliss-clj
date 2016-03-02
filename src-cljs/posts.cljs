(ns chromaticgliss.views.posts
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.forms :refer [input-field]]))

(def post-state
  (r/atom {:posts [{:id 12
                    :title "Posty McPostPost"
                    :slug "posty-mcpostpost"
                    :body "What what, yo yo."
                    :user_id 347
                    :created_at "12-12-12"}]}))


(defn post-editor [post-cur]
  [:form
   [input-field post-cur "text" "Title: " :title]
   [input-field post-cur "text" "Slug: " :slug]
   [input-field post-cur "textarea" "Body: " :body]
   [:input {:type "hidden"  :value (@post-cur :id)}]
   [:button {:type "button" :on-click #(POST "/api/posts" @post-cur)} "Save"]])

(defn post-component [post]
  [:div {:class "post" :id (post :id)}
   [:h1 (post :title)]
   [:div {:class "body"} (post :body)]
   [:div {:class "author"} (str "author: " (post :user_id))]
   [:div {:class "date"} (post :created_at)]])

(defn post-lister [posts]
  [:div {:id "all_posts"}
    (for [post posts]
        ^{:key (post :id)} [post-component post])])

(defn ^:export render-posts []
  ;; (swap! post-state assoc :posts (GET "/api/posts"))
  (.log js/console  (clj->js @post-state))
  (r/render [post-editor (r/cursor post-state [:posts 0])] (.getElementById js/document "app")))
