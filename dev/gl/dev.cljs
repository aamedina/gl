(ns gl.dev
  (:require [figwheel.client :as figwheel]
            [gl.core :as gl]
            [gl.net :as net]
            [gl.math :as math]
            [goog.vec.mat4f :as mat4]
            [goog.vec.Quaternion :as q]
            [goog.dom.animationFrame :as af]
            [gl.pipeline :as pipe]))

(figwheel/start {:build-id "dev"})

(gl/make-window 800 640)

(gl/clear-color 0 0 0 1)
(gl/clear-depth 1.0)
(gl/enable gl/DEPTH_TEST)
(gl/depth-func gl/LEQUAL)

(def vert (gl/load-shader "resources/public/glsl/dev.vert"))
(def frag (gl/load-shader "resources/public/glsl/dev.frag"))

(def vertices #js [ 1.0,  1.0,  0.0
                   -1.0,  1.0,  0.0
                    1.0,  -1.0,  0.0
                   -1.0,  -1.0,  0.0])

(def colors #js [1.0,  1.0,  1.0,  1.0,
                 1.0,  0.0,  0.0,  1.0,
                 0.0,  1.0,  0.0,  1.0,
                 0.0,  0.0,  1.0,  1.0])

(def prog (gl/make-program vert frag))

(let [vertex-color (gl/get-attrib-location prog "aVertexColor")
      vertex-pos (gl/get-attrib-location prog "aVertexPosition")
      pos-buf (gl/create-buffer)
      color-buf (gl/create-buffer)
      perspective-matrix (-> (math/mat4)
                             (mat4/makePerspective 45 (/ 800 640) 0.1 100.0))
      move-matrix (-> (math/mat4)
                      (mat4/translate 0.0 0.0 -6.0))]
  (gl/bind-buffer gl/ARRAY_BUFFER pos-buf)
  (gl/buffer-data gl/ARRAY_BUFFER (js/Float32Array. vertices) gl/STATIC_DRAW)
  (gl/bind-buffer gl/ARRAY_BUFFER color-buf)
  (gl/buffer-data gl/ARRAY_BUFFER (js/Float32Array. colors) gl/STATIC_DRAW)
  (gl/clear (bit-or gl/COLOR_BUFFER_BIT gl/DEPTH_BUFFER_BIT))
  (gl/enable-vertex-attrib-array vertex-color)
  (gl/enable-vertex-attrib-array vertex-pos)
  (gl/bind-buffer gl/ARRAY_BUFFER pos-buf)
  (gl/vertex-attrib-pointer vertex-pos 3 gl/FLOAT false 0 0)
  (gl/bind-buffer gl/ARRAY_BUFFER color-buf)
  (gl/vertex-attrib-pointer vertex-color 4 gl/FLOAT false 0 0)
  (gl/uniform-matrix4fv (gl/get-uniform-location prog "uPMatrix")
                        false perspective-matrix)
  (gl/uniform-matrix4fv (gl/get-uniform-location prog "uMVMatrix")
                        false move-matrix)
  (gl/draw-arrays gl/TRIANGLE_STRIP 0 4))
