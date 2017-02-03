(ns chromaticgliss.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [ajax.core :refer [GET POST DELETE default-interceptors]]
            [chromaticgliss.views.posts :refer [post-lister]]
            [chromaticgliss.views.login :refer [login-modal login-auth-interceptor]]))

(defn app []
  [:div {:id "app-contents"}
   [post-lister]
   [login-modal]])

(defn ^:export render-posts []
  (swap! default-interceptors (partial cons login-auth-interceptor))
  (r/render [app] (.getElementById js/document "app")))
