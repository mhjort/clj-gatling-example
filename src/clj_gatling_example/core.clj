(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [clj-gatling-example.simulations :refer [simulations]]
            [clj-time.core :as time])
  (:gen-class))

(defn -main [simulation users requests]
  (let [simulation (or ((keyword simulation) simulations)
                       (throw (Exception. (str "No such simulation " simulation))))]
    (gatling/run simulation {:concurrency (read-string users)
                             :root "tmp"
                             :requests (read-string requests)})))
