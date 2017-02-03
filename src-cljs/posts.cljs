(ns chromaticgliss.views.posts
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [re-com.modal-panel :refer [modal-panel]]
            [clojure.string :refer [join]]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.login :refer [login-modal is-logged-in show-login]]
            [chromaticgliss.views.shared :refer [input-field markdown-editor]]))

(def posts-state (r/atom {}))

(defn handle-error [_]
  (.log js/console "Error on request"))


(defn restore-post [post]
  (GET (str "/api/posts/id/" (@post :id))
       {:format :json
        :response-format :json
        :keywords? true
        :params @post
        :handler #(swap! post assoc :editing? false)
        :error-handler handle-error}))


(defn save-post [post]
  (POST (str "/api/posts/id/" (@post :id))
        {:format :json
         :response-format :json
         :keywords? true
         :params  (select-keys @post [:title :slug :body])
         :handler #(swap! post assoc :editing? false)
         :error-handler #(restore-post post)}))


(defn delete-post [post]
  (DELETE (str "/api/posts/id/" (@post :id))
          {:format :json
           :response-format :json
           :keywords? true
           :handler #(swap! post assoc :editing? false)
           :error-handler #(restore-post post)}))


(defn update-posts []
  (GET "/api/posts" {:format :json
                     :response-format :json
                     :keywords? true
                     :handler #(swap! posts-state assoc :posts (:results %))}))


(defn confirm-delete-dialog [post]
  (fn [post]
    [:div {:class "confirm-delete-dialog"}
     [:h1 "Are you sure, fam?"]
     [:div {:class "delete-post-buttons"}
      [:button
       {:type "button"
        :on-click #(delete-post post)}
       "Delete"]
      [:button
       {:type "button"
        :on-click #(swap! post assoc :confirm-delete? false)}
       "Cancel"]]]))


(defn confirm-delete-modal [post]
  (fn [post]
    (when (@post :confirm-delete?)
        [modal-panel
         :backdrop-color "grey"
         :backdrop-opacity 0.4
         :child [confirm-delete-dialog post]])))


(defn post-editor [post]
  "Post editor component. Expects post atom or cursor."
  [:form {:id (str "post-editor-" (@post :id)) :class "post-editor clearfix"}
   [:div {:id "input-container"}
    [input-field post "text" "Title: " :title]
    [input-field post "text" "Slug: " :slug]]
   [markdown-editor post "Body: " :body]
   [:input {:type "hidden"  :value (@post :id)}]
   [:button {:type "button" :on-click #(save-post post)} "Save"]
   [:button {:type "button" :on-click #(restore-post post)} "Cancel"]])


(defn post-component [post]
  "Displays post. Expects post atom or cursor."
  (fn [post]
    [:div {:id (str "post-"(@post :id)) :class "post"}
     [:h1 (@post :title)]
     [:div {:class "body"} (@post :body)]
     [:div {:class "author"} (str "author: " (@post :user_id))]
     [:div {:class "date"} (@post :created_at)]
     [:button {:type "button"
               :on-click #(if (is-logged-in)
                            (swap! post assoc :editing? true)
                            (show-login))}
      "Edit"]
     [:button {:type "button"
               :on-click #(swap! post assoc :confirm-delete? true)}
      "Delete"]
     [confirm-delete-modal post]]))

(defn post-lister []
  "Lists posts stored in local state"
  (do
    (update-posts)
    (fn []
      [:div {:id "all_posts"}
        (for [[i post] (map vector (iterate inc 0) (@posts-state :posts))]
          (if (post :editing?)
            ^{:key (str (post :id) "-ed")} [post-editor (r/cursor posts-state [:posts i])]
            ^{:key (post :id)} [post-component (r/cursor posts-state [:posts i])]))])))
