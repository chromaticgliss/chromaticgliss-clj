(ns chromaticgliss.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.posts :refer [post-lister]]
            [chromaticgliss.views.login :refer [login-modal]]))

(defn app []
  [:div {:id "app-contents"}
   [post-lister]
   [login-modal]])
  

(defn ^:export render-posts []
  (r/render [app] (.getElementById js/document "app")))
