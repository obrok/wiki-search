(ns wiki-search.storage
  (:gen-class)
  (:require [clojurewerkz.elastisch.rest :as esr]
            [clojurewerkz.elastisch.rest.index :as esi]
            [clojurewerkz.elastisch.rest.document :as esd]
            [clojurewerkz.elastisch.query :as esq]))

(def ^:private index-name "wiki-search")

(def ^:private mapping-types {"doc" {}})

(def ^:private max-results 1000)

(def es-endpoint
  (or (System/getenv "ELASTICSEARCH")
      "http://127.0.0.1:9200"))

(defn create
  "Creates the storage index"
  []
  (let [conn (esr/connect es-endpoint)]
    (esi/create conn index-name :mappings mapping-types)))

(defn destroy
  "Destroys the storage index"
  []
  (let [conn (esr/connect es-endpoint)]
    (esi/delete conn index-name)))

(defn refresh
  "Refreshes the storage index making submitted documents available for search
  immediately when this function returns as opposed to eventually"
  []
  (let [conn (esr/connect es-endpoint)]
    (esi/refresh conn index-name)))

(defn store
  "Writes a wiki item to storage"
  [item]
  (let [conn (esr/connect es-endpoint)
        id (:url item)]
    (esd/put conn index-name "doc" id item)))

(defn query
  "Searches the storage for items matching the given query"
  [q]
  (let [conn (esr/connect es-endpoint)]
    (->>
      (esd/search conn index-name "doc" {:query {:multi_match {:query q, :fields [:title :abstract]}}
                                         :size max-results})
      :hits
      :hits
      (map :_source))))
