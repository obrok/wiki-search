(defproject wiki-search "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [clojurewerkz/elastisch "2.1.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler wiki-search.core/app}
  :main ^:skip-aot wiki-search.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"seed" ["run" "-m" "wiki-search.seed"]
            "storage:create" ["run" "-m" "wiki-search.storage/create"]
            "storage:destroy" ["run" "-m" "wiki-search.storage/destroy"]})
