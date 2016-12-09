(defproject chromaticgliss "0.1.0-SNAPSHOT"
  :description "Steven Hemmy's (chromaticgliss) thought sandbox and portfolio."
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [buddy/buddy-hashers "1.1.0"]
                 [buddy/buddy-auth "1.3.0"]
                 [cheshire "5.6.3"]
                 [cljs-ajax "0.5.8"]
                 [compojure "1.5.1"]
                 [crypto-random "1.2.0"]
                 [hiccup "1.0.5"]
                 [korma "0.4.3"]
                 [org.postgresql/postgresql "9.4-1203-jdbc41"]
                 [ragtime "0.6.2"]
                 [reagent "0.6.0-alpha"]
                 [reagent-utils "0.1.7"]
                 [re-com "0.8.3"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-mock "0.3.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.2"]]


  :ring {:handler chromaticgliss.handler/app
         :nrepl {:start? true
                 :port 9998}
         :auto-reload? true
         :auto_refresh true}

  ;; Migrations are done is done via repl now
  ;; see https://github.com/weavejester/ragtime/wiki/Getting-Started
  ;; :ragtime {:migrations ragtime.sql.files/migrations
  ;;           :datastore "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}

  :cljsbuild {:builds [{:id "core"
                        :source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/core.js"
                                   :output-dir "resources/public/js/"
                                   :optimizations :whitespace
                                   :source-map "resources/public/js/core.js.map"}}]}
  :figwheel {}

  :profiles
  {:repl {:plugins [[cider/cider-nrepl "0.10.2"]]}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]
         :source-paths ["src-cljs"]}
   :test {:ragtime {:database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}}})
