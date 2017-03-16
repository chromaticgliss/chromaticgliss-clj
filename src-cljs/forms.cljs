(ns chromaticgliss.views.forms
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST DELETE]]))

(defn input-field
  ([cur type label field]
    (input-field cur type label field #(swap! cur assoc field (-> % .-target .-value))))
  ([cur type label field fn]
    [:div
    [:label label]
    [:input {:type type
             :value (@cur field)
             :on-change fn}]]))
