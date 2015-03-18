(ns gl.net
  (:require [cljs.core.async :as async :refer [chan <! >! alts! take! put!]]
            [goog.events :as e]
            [goog.events.EventTarget]
            [goog.events.EventType :as et]
            [goog.net.ImageLoader])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn load-image
  [url {:keys [cross-origin]}]
  (let [img (js/Image.)]
    (set! (.-src img) url)
    (when cross-origin
      (case cross-origin
        :anonymous (set! (.-crossOrigin img) "anonymous")
        :use-credentials (set! (.-crossOrigin img) "use-credentials")))
    img))
