(ns gl.math
  (:require [goog.vec.vec2f :as vec2]
            [goog.vec.vec3f :as vec3]
            [goog.vec.vec4f :as vec4]
            [goog.vec.mat3f :as mat3]
            [goog.vec.mat4f :as mat4])
  (:require-macros [gl.math]))

(extend-type js/Float32Array
  cljs.core/IEquiv
  (-equiv [o other]
    (and (== (alength o) (alength other))
         (loop [idx 0]
           (if (== (aget o idx) (aget other idx))
             (if (== (alength o) (inc idx))
               true
               (recur (inc idx)))
             false)))))

