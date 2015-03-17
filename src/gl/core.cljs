(ns gl.core
  (:require [goog.webgl :as gl])
  (:require-macros [gl.core :as gl :refer [generate-webgl-constants]]))

(defonce ^:const +canvas+
  (.createElement js/document "canvas"))

(defonce ^:const +gl+
  (or (.getContext +canvas+ "webgl")
      (.getContext +canvas+ "experimental-webgl")))

(generate-webgl-constants)

(defn resize
  [width height]
  (set! (.-width +canvas+) width)
  (set! (.-height +canvas+) height)
  (gl/viewport 0 0 width height))

(defn make-window
  [width height]
  (when-not (.contains js/document.body +canvas+)
    (.appendChild js/document.body +canvas+))
  (resize width height)
  +canvas+)
