(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [clojider.rc :as rc]
            [clj-gatling-example.simulations :as s :refer [simulations]]
            [clj-time.core :as t])
  (:gen-class))

(defn run-using-lambda [simulation users duration]
  (rc/run-simulation simulation {:concurrency users
                                 :bucket-name "clojider-results"
                                 :duration duration}))

(defn run-using-local-machine [simulation users duration]
  (gatling/run-simulation (eval simulation)
                          users
                          {:root "tmp"
                           :duration duration}))

(defn -main [runner-type simulation-str users-str duration-in-secs-str]
  (let [simulation (or ((keyword simulation-str) simulations)
                       (throw (Exception. (str "No such simulation " simulation-str))))
        users (read-string users-str)
        duration-in-secs (t/seconds (read-string duration-in-secs-str))]
    (if (= "lambda" runner-type)
      (run-using-lambda simulation users duration-in-secs)
      (run-using-local-machine simulation users duration-in-secs))))
