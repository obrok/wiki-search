(ns wiki-search.storage
  (:gen-class)
  (:require [clojurewerkz.elastisch.rest :as esr]
            [clojurewerkz.elastisch.rest.index :as esi]
            [clojurewerkz.elastisch.rest.document :as esd]))

(def ^:private index-name "wiki-search")

(def ^:private mapping-types {"doc" {}})

(defn- es-endpoint [] "http://127.0.0.1:9200")

(defn create
  "Creates the storage index"
  []
  (let [conn (esr/connect (es-endpoint))]
    (esi/create conn index-name :mappings mapping-types)))

(defn destroy
  "Destroys the storage index"
  []
  (let [conn (esr/connect (es-endpoint))]
    (esi/delete conn index-name)))

(defn store
  "Writes a wiki item to storage"
  [item]
  (let [conn (esr/connect (es-endpoint))
        id (get item "url")]
    (esd/put conn index-name "doc" id item)))
