(ns chromaticgliss.views.login
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST DELETE default-interceptors to-interceptor]]
            [chromaticgliss.views.shared :refer [input-field]]))

(def login-state (r/atom {:email nil :password nil :token nil}))

(def login-auth-interceptor
  (to-interceptor {:name "Authorization Interceptor"
                   :request #(assoc % :headers {"Authorization" (str "Token "(@login-state :token))})}))

(defn login-session []
  (POST "/api/sessions" {:format :json
                         :response-format :json
                         :keywords? true
                         :params (dissoc @login-state :token)
                         :handler #(swap! login-state assoc :token (% :auth-token))}))

(defn login-form []
  [:form
   [input-field login-state "text" "Email: " :email]
   [input-field login-state "password" "Password: " :password]
   [:div (:token @login-state)]
   [:button {:type "button" :on-click #(login-session)} "Save"]])

(defn ^:export render-login []
  ;; (swap! post-state assoc :posts (GET "/api/posts"))
  (swap! default-interceptors (partial cons login-auth-interceptor))
  (.log js/console  (clj->js @login-state))
  (r/render [login-form] (.getElementById js/document "app")))

