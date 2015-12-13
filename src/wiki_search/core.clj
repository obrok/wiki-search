(ns wiki-search.core
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/search" [] "{}"))

(def app
  (wrap-defaults app-routes site-defaults))
