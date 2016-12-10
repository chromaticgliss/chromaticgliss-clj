(ns chromaticgliss.views.shared
  (:require [reagent.core :as r]
            [re-com.modal-panel :as modal-panel]
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

(defn login-form
  []
  (let [shared-state (r/atom {:user []
                              :show-login? false})]
    [:form {:id "login-form"}
    [input-field (r/cursor shared-state [:user]) "text" "Username: " :username]
    [input-field (r/cursor shared-state [:user]) "password" "Password: " :password]]))

(defn login-modal
  "Modal for logging in to edit posts/pages/etc"
  []
    (when (@shared-state :show-login?)
       [modal-panel :backdrop-color "grey"
                    :backdrop-opacity 0.4
                    :child [login-form]]))

;; TODO add md processing
(defn markdown-editor
  ([cur label field]
    (markdown-editor cur label field #(swap! cur assoc field (-> % .-target .-value))))
  ([cur label field fn]
    [:div
      [:label label]
      [:textarea {:value (@cur field)
                  :on-change fn}]]))
