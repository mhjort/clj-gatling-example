(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling])
  (:gen-class))

(defn randomly-failing-request [user-id]
  (println (str "Simulating request for user #" user-id))
  (Thread/sleep (rand 1000))
  (> 0.7 (rand 1)))

(def test-scenario
  {:name "Test scenario"
   :requests [{:name "Request1" :fn randomly-failing-request}
              {:name "GoogleRequest" :http "http://google.fi"}]})


(def test-scenario2
  {:name "Test scenario2"
   :requests [{:name "Request1" :fn randomly-failing-request}
              {:name "AppleRequest" :http "http://apple.com"}]})

(defn -main [users]
  (gatling/run-simulation [test-scenario test-scenario2] (read-string users) {:root "tmp"}))
