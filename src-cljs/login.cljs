(ns chromaticgliss.views.login
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [re-com.modal-panel :refer [modal-panel]]
            [ajax.core :refer [GET POST DELETE to-interceptor]]
            [chromaticgliss.views.shared :refer [input-field]]
            [hodgepodge.core :refer [session-storage clear!]]))

(defn show-login []
  (session/swap! assoc-in [:login :show?] true))
  ;(js/console.log (clj->js (session/get :login))))

(defn hide-login []
  (session/swap! assoc-in [:login :show?] false))

(defn toggle-login []
  (session/swap! assoc-in [:login :show?] (not (session/get-in [:login :show?]))))

(def login-auth-interceptor
  (to-interceptor {:name "Authorization Interceptor"
                   :request #(assoc % :headers {"Authorization" (str "Token "(:login session-storage))})}))

(def login-auth-interceptor
  (to-interceptor {:name "Authorization Interceptor"
                   :request #(assoc % :headers {"Authorization" (str "Token "(@login-state :token))})}))

(defn login-session []
  (POST "/api/sessions" {:format :json
                         :response-format :json
                         :keywords? true
                         :params (session/get! :login)
                         :handler #(assoc! session-storage :token (% :auth-token))}))

(defn get-session []
  (GET "/api/sessions" {:format :json
                         :response-format :json
                         :keywords? true
                         :params (:login session-storage)
                         :handler identity}))

(defn login-form []
  [:form {:id "login-form"}
   [input-field (session/cursor [:login]) "text" "Email: " :email]
   [input-field (session/cursor [:login]) "password" "Password: " :password]
   [:div {:class "login-buttons"}
    [:button {:type "button"
              :on-click #(login-session)}
        "Login"]
    [:button {:type "button"
              :on-click hide-login}
        "Cancel"]]])

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
