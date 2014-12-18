(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [org.httpkit.client :as http]
            [clj-time.core :as time])
  (:gen-class))

(def base-url "http://localhost:9966/petclinic")

(defn- req [url user-id context callback]
  (let [check-status (fn [{:keys [status]}] (callback (= 200 status)))]
    (http/get (str base-url url) {} check-status)))

(def vets-scenario
  {:name "Veterinarians scenario"
   :requests [{:name "Front page" :fn (partial req "/")}
              {:name "Veterinarians" :fn (partial req "/vets.html")}]})

(defn -main [users requests]
  (gatling/run-simulation [vets-scenario]
                          (read-string users)
                          {:root "tmp" :requests (read-string requests)}))
