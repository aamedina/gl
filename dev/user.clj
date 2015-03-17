(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [figwheel-sidecar.repl-api :refer [cljs-repl]]))

(def system
  "A Var containing an object representing the application under
  development."
  nil)

(defn init
  "Creates and initializes the system under development in the Var
  #'system."
  [])

(defn start
  "Starts the system running, updates the Var #'system."
  [])

(defn stop
  "Stops the system if it is currently running, updates the Var
  #'system."
  [])

(defn go
  "Initializes and starts the system running."
  []
  (init)
  (start)
  :ready)

(defn reset
  "Stops the system, reloads modified source files, and restarts it."
  []
  (stop)
  (refresh :after 'user/go))
