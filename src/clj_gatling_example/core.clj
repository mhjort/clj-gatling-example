(ns clj-gatling-example.core
  (:require [clj-gatling.core :as gatling]
            [org.httpkit.client :as http]
            [clj-time.core :as time])
  (:gen-class))

(def base-url "http://clj-gatling-demo-server.herokuapp.com")

(defn- req [url user-id context callback]
  (let [check-status (fn [{:keys [status]}] (callback (= 200 status)))]
    (http/get (str base-url url) {} check-status)))

(def ping-scenario
  {:name "Ping scenario"
   :requests [{:name "Ping Endpoint" :fn (partial req "/ping")}]})

(defn -main [users requests]
  (gatling/run-simulation [ping-scenario]
                          (read-string users)
                          {:root "tmp" :requests (read-string requests)}))
