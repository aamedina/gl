(ns gl.pipeline
  (:require [gl.core :as gl]
            [gl.math :as math]
            [goog.vec.mat4f :as mat4]))

(comment
  data Camera
  data Object = Geometry
  data Light
  data Shader
  data Texture
  data Material
  data Image2D
  
  render :: Camera -> Objects -> Lights -> Shaders -> Textures -> Image2D)

(defprotocol Camera)

(defn ^array perspective-camera
  [^number fov ^number aspect ^number near ^number far]
  (specify! (mat4/makePerspective (math/mat4) fov aspect near far)
    Camera))

(defn ^array orthographic-camera
  [^number left ^number right ^number bottom ^number top
   ^number near ^number far]
  (specify! (mat4/makeOrtho (math/mat4) left right bottom top near far)
    Camera))

(deftype Object [geometry])

(deftype Light [])

(deftype Shader [])

(deftype Texture [])

(deftype Material [])

(defn render
  [camera objects lights shaders]
  )
