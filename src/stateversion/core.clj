(ns stateversion.core
  (:gen-class)
  (:require [clojure.java.io :as io])
  (:import (org.elasticsearch.client Requests)
           (org.elasticsearch.common.settings ImmutableSettings)
           (org.elasticsearch.common.transport InetSocketTransportAddress)
           (org.elasticsearch.client.transport TransportClient)
           (java.text SimpleDateFormat)
           (java.util Date)))

(defn make-transport-client [{:keys [host port cluster] :as settings}]
  (let [s (doto (ImmutableSettings/settingsBuilder)
            (.put "cluster.name" cluster))]
    (doto (TransportClient. s)
      (.addTransportAddress
       (InetSocketTransportAddress. host (Integer. port))))))

(defn addrs []
  (->> (java.net.NetworkInterface/getNetworkInterfaces)
       enumeration-seq
       (filter #(not (.isLoopback %)))
       (mapcat #(enumeration-seq (.getInetAddresses %)))
       (map #(.getHostAddress %))
       (interpose " ")
       (apply str)))

(defn -main [& args]
  (let [[cluster host port] args]
    (if (or (= cluster "-h") (= cluster "-help") (= cluster "--help"))
      (println "usage: stateversion [CLUSTER] [HOST] [PORT]")
      (with-open [client (make-transport-client
                          {:host (or host "localhost")
                           :port (or port "9300")
                           :cluster (or cluster "elasticsearch")})]
        (let [state (-> client .admin .cluster
                        .prepareState (.setLocal true)
                        .execute .actionGet)]
          (printf "%s %s %s %s\n"
                  (int (/ (System/currentTimeMillis) 1000))
                  (.format (SimpleDateFormat. "HH:mm:ss") (Date.))
                  (addrs)
                  (-> state .getState .version))))))
  (flush))
