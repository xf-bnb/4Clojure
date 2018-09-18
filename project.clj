(defproject four-clojure "1.0.0-SNAPSHOT"
  :description "solution for 4Clojure"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://github.com/xf-bnb/four-clojure/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repositories [["clojars" {:url "https://repo.clojars.org/"}]
                 ["maven-central" {:url "https://repo1.maven.org/maven2"}]]
  :source-paths ["src"])
