(defproject four-clojure "1.0.0-SNAPSHOT"
  :description "solution for 4Clojure"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://github.com/xf-bnb/four-clojure/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot four-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
