(ns gl.dev
  (:require [figwheel.client :as figwheel]
            [gl.core :as gl]
            [gl.net :as net]))

(figwheel/start {:build-id "dev"})

(gl/make-window 800 640)

(gl/clear-color 0 0 0 1)
(gl/enable gl/DEPTH_TEST)
(gl/depth-func gl/LEQUAL)
(gl/clear (bit-or gl/COLOR_BUFFER_BIT gl/DEPTH_BUFFER_BIT))

(def vert (gl/load-shader "resources/public/glsl/dev.vert"))
(def frag (gl/load-shader "resources/public/glsl/dev.frag"))

(def colors #js [1.0,  1.0,  1.0,  1.0,
                 1.0,  0.0,  0.0,  1.0,
                 0.0,  1.0,  0.0,  1.0,
                 0.0,  0.0,  1.0,  1.0])

(def buf (gl/create-buffer))

(gl/bind-buffer gl/ARRAY_BUFFER buf)
(gl/buffer-data gl/ARRAY_BUFFER (js/Float32Array. colors) gl/STATIC_DRAW)

(def prog (gl/make-program vert frag))
