(defproject gl "0.1.0-SNAPSHOT"
  :description "Minimalist ClojureScript interface to WebGL"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [org.clojure/clojurescript "0.0-3126"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]
  :plugins [[lein-cljsbuild "1.0.5"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.10"]
                                  [figwheel "0.2.5"]]
                   :plugins [[lein-figwheel "0.2.5"]]
                   :source-paths ["dev"]
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :css-dirs "resources/public/css"
                              :nrepl-port 4005
                              :server-logfile ".figwheel"}}}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src" "dev"]
                        :compiler {:main gl.dev
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/out"
                                   :asset-path "js/out"
                                   :optimizations :none
                                   :cache-analysis true
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["src" "dev"]
                        :compiler {:main gl.dev
                                   :output-to "resources/public/js/main.js"
                                   :asset-path "js/out"
                                   :pretty-print false
                                   :optimizations :advanced
                                   :source-map true}}]}
  :jvm-opts ^:replace ["-Xmx512m" "-server"])
