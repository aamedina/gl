(ns gl.dev
  (:require [figwheel.client :as figwheel]
            [gl.core :as gl]))

(figwheel/start {:build-id "dev"})

(gl/make-window 800 640)

(gl/clear-color 0 0 0 1)
(gl/enable gl/DEPTH_TEST)
(gl/depth-func gl/LEQUAL)
(gl/clear (bit-or gl/COLOR_BUFFER_BIT gl/DEPTH_BUFFER_BIT))

