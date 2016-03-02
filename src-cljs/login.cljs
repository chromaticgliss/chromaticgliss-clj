(ns chromaticgliss.views.login
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST DELETE]]
            [chromaticgliss.views.forms :refer [input-field]]))

(def login-state (r/atom {:email nil :password nil :token nil}))

(defn login-session []
  (let [resp (POST "/api/sessions" {:format :json
                                    :response-format :json
                                    :keywords? true
                                    :params {:email (:email @login-state)
                                             :password (:password @login-state)}
                                    :handler #(swap! login-state assoc :token (% :auth-token))})]))

(defn login-form []
  [:form
   [input-field login-state "text" "Email: " :email]
   [input-field login-state "password" "Password: " :password]
   [:div (:token @login-state)]
   [:button {:type "button" :on-click #(login-session)} "Save"]])

(defn ^:export render-login []
  ;; (swap! post-state assoc :posts (GET "/api/posts"))
  (.log js/console  (clj->js @login-state))
  (r/render [login-form] (.getElementById js/document "app")))

