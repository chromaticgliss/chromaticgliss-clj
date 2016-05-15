(ns chromaticgliss.views.main
  (:use hiccup.core
        hiccup.page))

(defn head-section []
  (html [:head
          [:meta {:charset "utf-8"}]
          (include-css "/css/core.css")
          [:title "(chromaticgliss \"code\" \"music\" \"math\")"]]))

(defn header-section []
  (html [:h1 "(chromaticgliss \"code\" \"music\" \"math\")"]
        [:p "I'm not your run-of-the-mill codeslinger. My code's got guts, young'n."]))

(defn body-section []
  (html [:body
          (header-section)
          [:div {:id "app"}]
          (include-js "/js/core.js")
          [:script "chromaticgliss.views.posts.render_posts();"]]))

(defn index-page []
  (html5
    (head-section)
    (body-section)))
