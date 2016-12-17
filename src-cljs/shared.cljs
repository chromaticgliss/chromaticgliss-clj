(ns chromaticgliss.views.shared
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST DELETE]]))

(defn input-field
  ([cur type label field]
   (input-field cur type label field #(swap! cur assoc field (-> % .-target .-value))))
  ([cur type label field fn]
   [:div {:class "input-container"}
    [:label label]
    [:input {:type type
             :value (@cur field)
             :on-change fn}]]))

;; TODO add md processing
(defn markdown-editor
  ([cur label field]
    (markdown-editor cur label field #(swap! cur assoc field (-> % .-target .-value))))
  ([cur label field fn]
    [:div
      [:label label]
      [:textarea {:value (@cur field)
                  :on-change fn}]]))
