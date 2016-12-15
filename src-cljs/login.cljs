(ns chromaticgliss.views.login
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [re-com.modal-panel :refer [modal-panel]]
            [ajax.core :refer [GET POST DELETE default-interceptors to-interceptor]]
            [chromaticgliss.views.shared :refer [input-field]]))

(def login-auth-interceptor
  (to-interceptor {:name "Authorization Interceptor"
                   :request #(assoc % :headers {"Authorization" (str "Token "(session/get-in [:login :token]))})}))

(defn login-session []
  (POST "/api/sessions" {:format :json
                         :response-format :json
                         :keywords? true
                         :params (session/get! [:login :token])
                         :handler #(session/assoc-in! [:login :token] (% :auth-token))}))

(defn get-session []
  (GET "/api/sessions" {:format :json
                         :response-format :json
                         :keywords? true
                         :params (session/get :login :token)
                         :handler identity}))

(defn login-form []
      [:form {:id "login-form"}
       [input-field (session/cursor [:login]) "text" "Email: " :email]
       [input-field (session/cursor [:login]) "password" "Password: " :password]
       [:div (session/get-in [:login :token])]
       [:button {:type "button"
                 :on-click #(login-session)} "Login"]])

(defn login-modal []
  "Modal for logging in to edit posts/pages/etc"
  (let [shared-state (r/atom false)]
    (fn []
      (when (@shared-state :show-login?)
          [modal-panel :backdrop-color "grey"
                      :backdrop-opacity 0.4
                      :child [login-form]]))))

(defn show-login []
  (print "showloginnnnnnnnnnnnnn"))

(defn is-logged-in []
  (print "isloggedinnnnnnnnn"))


(defn ^:export render-login []
  (swap! default-interceptors (partial cons login-auth-interceptor))
  (.log js/console  (clj->js (session/get :login)))
  (r/render [login-form] (.getElementById js/document "app")))
