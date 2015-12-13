(ns wiki-search.seed
  (:gen-class)
  (:require [clj-http.client :as http]
            [wiki-search.storage :as storage])
  (:use [clojure.data.xml :only (parse)]))

(def ^:private wiki-dump-path "http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-abstract23.xml")

(defn- property-text
  "Extracts the text content of xml tag"
  [property doc]
  (->> doc
       :content
       (filter #(= (:tag %) property))
       first
       :content
       first))

(defn- parse-item
  "Parses a single wiki dump doc item into the internal format"
  [doc]
  {:title (property-text :title doc)
   :url (property-text :url doc)
   :abstract (property-text :abstract doc)})

(defn parse-feed
  "Parses a wiki dump xml into the internal format"
  [doc]
  (->> doc
       :content
       (map parse-item)))

(defn -main
  "Seeds the storage with a downloaded wiki excerpts xml"
  [& args]
  (let [{status :status, stream :body} (http/get wiki-dump-path {:as :stream})]
    (assert (= status 200))
    (doseq [doc (->> stream parse parse-feed)]
      (println (:url doc))
      (storage/store doc))))
