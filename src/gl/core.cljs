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

(defn make-shader
  [type source]
  (let [shader (gl/create-shader (case type
                                   :vertex VERTEX_SHADER
                                   :fragment FRAGMENT_SHADER))]
    
    (gl/shader-source shader source)
    (gl/compile-shader shader)
    
    (when (false? (gl/get-shader-parameter shader COMPILE_STATUS))
      (throw (gl/get-shader-info-log shader)))
    
    shader))

(defn make-program
  [vertex-source fragment-source]
  (let [vertex-shader (make-shader :vertex vertex-source)
        fragment-shader (make-shader :fragment fragment-source)
        program (gl/create-program)]
    
    (gl/attach-shader program vertex-shader)
    (gl/attach-shader program fragment-shader)
    (gl/bind-attrib-location program 0 "position")
    (gl/link-program program)
    
    (when (false? (gl/get-program-parameter program LINK_STATUS))
      (let [m {:info (gl/get-program-info-log program)
               :status (gl/get-program-parameter program VALIDATE_STATUS)}]
        (throw (ex-info (gl/get-error) m))))
    
    (gl/delete-shader vertex-shader)
    (gl/delete-shader fragment-shader)

    (gl/use-program program)
    
    program))
