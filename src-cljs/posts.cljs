(ns chromaticgliss.views.posts
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [clojure.string :refer [join]]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.login :refer [login-modal is-logged-in show-login]]
            [chromaticgliss.views.shared :refer [input-field markdown-editor]]))

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

(defn confirm-delete [post-cur])

(defn post-editor [post]
  "Post editor component. Expects post atom or cursor."
  [:form {:id (str "post-editor-" (@post :id)) :class "post-editor"}
   [input-field post "text" "Title: " :title]
   [input-field post "text" "Slug: " :slug]
   [markdown-editor post "Body: " :body]
   [:input {:type "hidden"  :value (@post :id)}]
   [:button {:type "button" :on-click #(save-post post)} "Save"]
   [:button {:type "button" :on-click #(restore-post post)} "Cancel"]])

(defn post-component [post]
  "Displays post. Expects post atom or cursor."
  [:div {:id (str "post-"(@post :id)) :class "post"}
   [:h1 (@post :title)]
   [:div {:class "body"} (@post :body)]
   [:div {:class "author"} (str "author: " (@post :user_id))]
   [:div {:class "date"} (@post :created_at)]
   [:button {:type "button" :on-click #(if (is-logged-in)
                                         (swap! post assoc :editing? true)
                                         (show-login))} "Edit"]
   [:button {:type "button" :on-click #(confirm-delete post)} "Delete"]])

(defn post-lister []
  "Lists posts stored in local state"
  (let [posts (r/atom [])]
    (GET "/api/posts" {:format :json
                       :response-format :json
                       :keywords? true
                       :handler #(reset! posts (:results %))})
    (fn []
      [:div {:id "all_posts"}
       (for [[i post] (map vector (iterate inc 0) @posts)]
         (if (post :editing?)
           ^{:key (str (post :id) "-ed")} [post-editor (r/cursor posts [i])]
           ^{:key (post :id)} [post-component (r/cursor posts [i])]))])))
