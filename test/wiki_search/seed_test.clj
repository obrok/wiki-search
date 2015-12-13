(ns wiki-search.seed-test
  (:require [clojure.test :refer :all]
            [wiki-search.seed :refer :all])
  (:use [clojure.data.xml :only (parse-str)]))

(def sample-xml
  "<feed>
  <doc>
  <title>Wikipedia: IFK Holmsund</title>
  <url>http://en.wikipedia.org/wiki/IFK_Holmsund</url>
  <abstract>Idrottsföreningen Kamraterna Holmsund was...</abstract>
  </doc>
  </feed>")

(deftest test-parsing
  (testing "Parsing a feed with a single item"
    (let [input (parse-str sample-xml)]
      (is (=
           (parse-feed input)
           [{:title "Wikipedia: IFK Holmsund"
             :url "http://en.wikipedia.org/wiki/IFK_Holmsund"
             :abstract "Idrottsföreningen Kamraterna Holmsund was..."}])))))
