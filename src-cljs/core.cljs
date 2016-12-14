(ns chromaticgliss.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.posts :refer [post-lister]]))

(defn ^:export render-posts []
  (GET "/api/posts" {:format :json
                     :response-format :json
                     :keywords? true
                     :handler #(session/swap! assoc :posts (% :results))})
  (r/render [post-lister (.getElementById js/document "app")]))
