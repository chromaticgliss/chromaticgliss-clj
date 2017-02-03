(defproject chromaticgliss "0.1.0-SNAPSHOT"
  :description "Steven Hemmy's (chromaticgliss) thought sandbox and portfolio."
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [buddy/buddy-hashers "1.1.0"]
                 [buddy/buddy-auth "1.3.0"]
                 [cheshire "5.6.3"]
                 [cljs-ajax "0.5.8"]
                 [compojure "1.5.1"]
                 [crypto-random "1.2.0"]
                 [hiccup "1.0.5"]
                 [funcool/hodgepodge "0.1.4"]
                 [korma "0.4.3"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [ragtime "0.6.2"]
                 [reagent-utils "0.2.0"]
                 [reagent "0.6.0"]
                 [re-com "0.8.3"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-mock "0.3.0"]
                 [secretary "1.2.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.5"]]

  :clean-targets ^{:protect false}
    [:target-path
        [:cljsbuild :builds :core :compiler :output-dir]
        [:cljsbuild :builds :core :compiler :output-to]]

  :ring {:handler chromaticgliss.handler/app
         :nrepl {:start? true}
         :auto-reload? true
         :auto_refresh true}

  ;; Migrations are done is done via repl now
  ;; see https://github.com/weavejester/ragtime/wiki/Getting-Started
  ;; :ragtime {:migrations ragtime.sql.files/migrations
  ;;           :datastore "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}

  :cljsbuild {:builds [{:id "core"
                        :source-paths ["src-cljs"]
                        :figwheel true
                        :compiler {:main chromaticgliss.core
                                   :asset-path "js"
                                   :output-to "resources/public/js/core.js"
                                   :output-dir "resources/public/js/"
                                   :optimizations :none
                                   :source-map true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev  {:dependencies [[javax.servlet/servlet-api "2.5"]
                                   [com.cemerick/piggieback "0.2.1"]
                                   [figwheel-sidecar "0.5.8"]
                                   [ring/ring-mock "0.3.0"]]
                    :plugins [[lein-figwheel "0.5.8"]]
                    :repl {:plugins [[cider/cider-nrepl "0.14.0"]]}
                    :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                    :source-paths ["src-cljs"]}
             :test {:ragtime {:database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}}})
