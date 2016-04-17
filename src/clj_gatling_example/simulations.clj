(ns clj-gatling-example.simulations
  (:require [clojure.core.async :refer [go chan >!]]
            [org.httpkit.client :as http]))

(def base-url "http://clj-gatling-demo-server.herokuapp.com")

(defn- http-get [url ctx]
  (let [return (chan)
        check-status (fn [{:keys [status]}]
                       (go (>! return [(= 200 status) ctx])))]
    (http/get (str base-url url) {} check-status)
    return))

(defn- http-get-with-ids [url ids {:keys [user-id] :as ctx}]
  (let [return (chan)
        check-status (fn [{:keys [status]}]
                       (go (>! return [(= 200 status) ctx])))
        id (nth ids user-id)]
    (http/get (str base-url url id) {} check-status)
    return))

(def ping
  (partial http-get "/ping"))

(def ping-simulation
  {:name "ping-simulation"
   :scenarios [{:name "Ping scenario"
                :steps [{:name "Ping Endpoint" :request ping}]}]})

(def article-read
  (partial http-get-with-ids "/metrics/article/read/" (cycle (range 100 200))))

(def program-start
  (partial http-get-with-ids "/metrics/program/start/" (cycle (range 200 400))))

(def metrics-simulation
  {:name "metrics-simulation"
   :scenarios [{:name "Article read scenario"
                :weight 2
                :steps [{:name "Article read request"
                         :request article-read}]}
               {:name "Program start scenario"
                :weight 1
                :steps [{:name "Program start request"
                         :request program-start}]}]})

(def simulations
  {:ping ping-simulation
   :metrics metrics-simulation})
