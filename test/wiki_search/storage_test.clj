(ns wiki-search.storage-test
  (:require [clojure.test :refer :all]
            [wiki-search.storage :refer :all]))

(deftest storage-basic-action
  (with-redefs
    [wiki-search.storage/index-name "wiki-search-test"]
    (destroy)
    (create)
    (store {:url "http://example.com/Storage", :title "An essay on storage"})
    (store {:url "http://example.com/Never", :title "Never returned"})
    (refresh)
    (testing "You can query for things that have been stored"
      (is (= (query "essay") [{:url "http://example.com/Storage", :title "An essay on storage"}])))
    (testing "Things that do not match are not returned"
      (is (= (query "discombobulator") [])))))
