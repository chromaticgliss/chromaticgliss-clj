(ns chromaticgliss.db
  (:use korma.db)
  (:require [environ.core :refer [env]]))

(defdb db (postgres {:db "chromaticgliss"
                     :user "chromaticgliss"
                     :password "***REMOVED***"
                     :host "localhost"
                     :port "5432"}))
