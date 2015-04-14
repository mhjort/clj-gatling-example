(ns clj-gatling-example.simulations
  (:require [org.httpkit.client :as http]))

(def base-url "http://clj-gatling-demo-server.herokuapp.com")

(defn- http-get [url user-id context callback]
  (let [check-status (fn [{:keys [status]}] (callback (= 200 status)))]
    (http/get (str base-url url) {} check-status)))

(defn- http-get-with-ids [url ids user-id context callback]
  (let [check-status (fn [{:keys [status]}] (callback (= 200 status)))
        id (nth ids user-id)]
    (http/get (str base-url url id) {} check-status)))

(def ping-simulation
  [{:name "Ping scenario"
    :requests [{:name "Ping Endpoint" :fn (partial http-get "/ping")}]}])

(def article-ids (cycle (range 100 300)))

(def program-ids (cycle (range 200 500)))

(def metrics-simulation
  [{:name "Article read scenario"
    :weight 2
    :requests [{:name "Article read request"
                :fn (partial http-get-with-ids "/metrics/article/read/" article-ids)}]}
   {:name "Program start scenario"
    :weight 1
    :requests [{:name "Program start request"
                :fn (partial http-get-with-ids "/metrics/program/start/" program-ids)}]}])

(def simulations
  {:ping ping-simulation
   :metrics metrics-simulation})
