(defproject clj-gatling-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :profiles {:local {:dependencies [[org.clojure/tools.nrepl "0.2.11"]
                                    [clj-gatling "0.7.5"]]
                     :main clj-gatling-example.core}
             :rc {:dependencies [[org.clojure/tools.nrepl "0.2.11"]
                                 [clojider-remote "0.1.0-SNAPSHOT"]]}
             :lambda {:dependencies [[clojider-lambda "0.1.0-SNAPSHOT"]]}})
