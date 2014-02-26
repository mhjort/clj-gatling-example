(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [org.httpkit.client :as http])
  (:gen-class))

(defn randomly-failing-request [user-id]
  (println (str "Simulating request for user #" user-id))
  (Thread/sleep (rand 1000))
  (> 0.7 (rand 1)))

(defn http-request [url user-id]
  (let [{:keys [status headers body error] :as resp} @(http/get url)]
    (= 200 status)))

(def test-scenario
  {:name "Test scenario"
   :requests [{:name "Request1" :fn randomly-failing-request}
              {:name "GoogleRequest" :fn (partial http-request "http://www.google.fi")}]})

(defn -main [users]
  (gatling/run-simulation test-scenario (read-string users)))
