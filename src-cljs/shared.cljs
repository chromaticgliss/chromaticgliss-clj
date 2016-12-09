(ns chromaticgliss.views.shared
  (:require [reagent.core :as r]
            [garden.core :refer [css]]
            [ajax.core :refer [GET POST DELETE]]))

(defn modal-window [prefix comp fn & args]
  [:div {:id (str prefix "overlay") :class "overlay" :style {:display "none"}}
   [:div {:id (str prefix "modal") :class "modal"}
    (comp)
    [:button {:type "button" :on-click #(close-modal %)} "Okay"]
    [:button {:type "button" :on-click #(swap! modal-show false) } "Cancel"]]])

(defn close-modal [])

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
   [:div {:class "markdown-container"}
    [:label label]
    [:textarea {:value (@cur field)
                :on-change fn}]]))
