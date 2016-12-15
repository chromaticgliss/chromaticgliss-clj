(ns chromaticgliss.views.main
  (:use hiccup.core
        hiccup.page))

(defn css-includes []
  (html
    (include-css "//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.css")
    (include-css "/css/material-design-iconic-font.min.css")
    (include-css "/css/re-com.css")
    (include-css "/css/core.css")))

(defn head-section []
  (html [:head
          [:meta {:charset "utf-8"}]
          (css-includes)
          [:title "(chromaticgliss \"code\" \"music\" \"math\")"]]))

(defn header-section []
  (html [:header {:id "main-header"}
         [:h1 "(chromaticgliss \"code\" \"music\" \"math\")"]
         [:p "I'm not your run-of-the-mill codeslinger. My code's got guts, young'n."]]))

(defn body-section []
  (html [:body
         [:div {:id "main-wrapper"}
          (header-section)
          [:div {:id "app"}]
          (include-js "/js/core.js")
          [:script "chromaticgliss.core.render_posts();"]]]))

(defn index-page []
  (html5
    (head-section)
    (body-section)))
