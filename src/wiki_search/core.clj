(ns wiki-search.core
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :refer [response header]]
            [clojure.data.json :refer [write-str]]
            [wiki-search.storage :refer [query]]))

(defroutes app-routes
  (GET "/search" request
    (let [q (->> request :params :q)]
      (->
        (response (write-str {:q q, :reqults (query q)}))
        (header "Content-Type" "application/json")))))

(def app
  (wrap-defaults app-routes site-defaults))
