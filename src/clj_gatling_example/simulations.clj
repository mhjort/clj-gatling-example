(ns clj-gatling-example.simulations
  (:require [org.httpkit.client :as http]))

(def base-url "http://clj-gatling-demo-server.herokuapp.com")

(defn- http-get [url user-id context callback]
  (let [check-status (fn [{:keys [status]}] (callback (= 200 status)))]
    (http/get (str base-url url) {} check-status)))

(def ping-simulation
  [{:name "Ping scenario"
   :requests [{:name "Ping Endpoint" :fn (partial http-get "/ping")}]}])

(def simulations
  {:ping ping-simulation})
