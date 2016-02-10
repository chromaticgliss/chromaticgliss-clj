(defproject chromaticgliss "0.1.0-SNAPSHOT"
  :description "Steven Hemmy's (chromaticgliss) thought sandbox and portfolio."
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-mock "0.3.0"]
                 [cheshire "5.5.0"]
                 [korma "0.4.2"]
                 [org.postgresql/postgresql "9.4-1203-jdbc41"]
                 [ragtime "0.3.4"]
                 [environ "1.0.2"]
                 [buddy/buddy-hashers "0.11.0"]
                 [buddy/buddy-auth "0.9.0"]
                 [crypto-random "1.2.0"]]
  :plugins [[lein-ring "0.9.7"]
            [ragtime/ragtime.lein "0.3.6"]]

  :ring {:handler chromaticgliss.handler/app
         :nrepl {:start? true
                 :port 9998}}
  :ragtime {:migrations ragtime.sql.files/migrations
            :database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}
   :test {:ragtime {:database "jdbc:postgresql://localhost:5432/chromaticgliss?user=chromaticgliss&password=password"}}})
