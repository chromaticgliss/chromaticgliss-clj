(ns chromaticgliss.db
  (:use korma.db))

(defdb db (postgres {:db "chromaticgliss"
                     :user "chromaticgliss"
                     :password "***REMOVED***"
                     :host "localhost"
                     :port "5432"}))
