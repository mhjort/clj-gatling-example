(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [clj-gatling-example.simulations :refer [simulations]]
            [clj-time.core :as time])
  (:gen-class))

(defn -main [simulation users requests & [option]]
  (let [simulation (or ((keyword simulation) simulations)
                       (throw (Exception. (str "No such simulation " simulation))))]
    (if (= "--no-report" option)
      (gatling/run simulation
                 {:concurrency (read-string users)
                  :reporter {:writer (fn [_ _ _])
                             :generator (fn [simulation]
                                          (println "Ran" simulation "without report"))}
                  :requests (read-string requests)})
    (gatling/run simulation
                 {:concurrency (read-string users)
                  :root "tmp"
                  :requests (read-string requests)}))))
