(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling])
  (:gen-class))

(defn run-request [id]
  (Thread/sleep (rand 1000))
  (> 0.7 (rand 1)))

(def test-scenario
  {:name "Test scenario"
   :requests [{:name "Request1" :fn run-request}
              {:name "Request2" :fn run-request}]})

(defn -main [users]
  (gatling/run-simulation test-scenario (read-string users)))

