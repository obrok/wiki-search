(ns wiki-search.storage-test
  (:require [clojure.test :refer :all]
            [wiki-search.storage :refer :all]))

(def essay {:url "http://example.com/Storage", :title "An essay on storage", :abstract "A boring essay"})
(def story {:url "http://example.com/Story", :title "A storage story", :abstract "An interesting story"})

(deftest storage-basic-action
  (with-redefs
    [wiki-search.storage/index-name "wiki-search-test"]

    (destroy)
    (create)
    (store essay)
    (store story)
    (refresh)

    (testing "You can query for things that have been stored"
      (is (= (query "essay") [essay])))

    (testing "Multiple results can be returned"
      (is (= (set (query "storage")) #{essay story})))

    (testing "Querying by abstract"
      (is (= (query "boring") [essay])))

    (testing "Things that do not match are not returned"
      (is (= (query "discombobulator") [])))))
