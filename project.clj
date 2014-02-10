(defproject stateversion "0.0.1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.elasticsearch/elasticsearch "0.90.11"]
                 [log4j/log4j "1.2.17"]]
  :plugins [[lein-bin "0.3.0"]]
  :uberjar-name "state.jar"
  :jvm-opts ["-XX:TieredStopAtLevel=1"
             "-XX:+TieredCompilation"]
  :bin {:bootclasspath :yes!}
  :main stateversion.core)
