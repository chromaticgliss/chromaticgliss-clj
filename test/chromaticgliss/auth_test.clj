(ns chromaticgliss.auth-test
  (:use clojure.test
        chromaticgliss.test-core)
  (:require [chromaticgliss.auth :as auth]
            [chromaticgliss.models.users :as u]
            [korma.core :as sql]))

(use-fixtures :each with-rollback)

(deftest authenticating-user
  (let [user (u/create {:name "Test" :email "user@example.com" :password "s3cr3t"})]
    (testing "Authenticates with valid token"
      (let [token (auth/make-token! (:id user))]
        (is (= user (auth/authenticate-token {} token)))))
    (testing "Does not authenticate with nonexistent token"
      (is (nil? (auth/authenticate-token {} "youavetobekiddingme"))))
    (testing "Does not authenticate with expired token"
      (let [token (auth/make-token! (:id user))
            sql (str "UPDATE auth_tokens "
                     "SET created_at = NOW() - interval '7 hours' "
                     "WHERE id = ?")]
        (sql/exec-raw [sql [token]])
        (is (nil? (auth/authenticate-token {} token)))))))
