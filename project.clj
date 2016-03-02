(defproject chromaticgliss "0.1.0-SNAPSHOT"
  :description "Steven Hemmy's (chromaticgliss) thought sandbox and portfolio."
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [buddy/buddy-hashers "0.11.0"]
                 [buddy/buddy-auth "0.9.0"]
                 [cheshire "5.5.0"]
                 [cljs-ajax "0.5.3"]
                 [compojure "1.4.0"]
                 [crypto-random "1.2.0"]
                 [hiccup "1.0.5"]
                 [korma "0.4.2"]
                 [org.postgresql/postgresql "9.4-1203-jdbc41"]
                 [ragtime "0.3.4"]
                 [reagent "0.6.0-alpha"]
                 [reagent-utils "0.1.7"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-mock "0.3.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.2"]
            [ragtime/ragtime.lein "0.3.6"]]


  :ring {:handler chromaticgliss.handler/app
         :nrepl {:start? true
                 :port 9998}}

  :ragtime {:migrations ragtime.sql.files/migrations
            :database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}

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
                        [org.clojure/tools.nrepl "0.2.10"]
                        [ring/ring-mock "0.3.0"]]
         :source-paths ["src-cljs"]}
   :test {:ragtime {:database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}}})
