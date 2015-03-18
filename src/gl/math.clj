(ns gl.math)

(defmacro vec2
  ([] `(js/Float32Array. 2))
  ([x] `(let [x# ~x] (vec2 x# x#)))
  ([x y] `(doto (vec2) (aset 0 ~x) (aset 1 ~y))))

(defmacro vec3
  ([] `(js/Float32Array. 3))
  ([x] `(let [x# ~x] (vec3 x# x# x#)))
  ([x y z] `(doto (vec3) (aset 0 ~x) (aset 1 ~y) (aset 2 ~z))))

(defmacro vec4
  ([] `(js/Float32Array. 4))
  ([x] `(let [x# ~x] (vec4 x# x# x# x#)))
  ([x y z w] `(doto (vec4) (aset 0 ~x) (aset 1 ~y) (aset 2 ~z) (aset 3 ~w))))

(defmacro mat3
  ([] `(mat3 1 0 0 0 1 0 0 0 1))
  ([v00 v10 v20 v01 v11 v21 v02 v12 v22]
   `(doto (js/Float32Array. 9)
      (aset 0 ~v00)
      (aset 1 ~v10)
      (aset 2 ~v20)
      (aset 3 ~v01)
      (aset 4 ~v11)
      (aset 5 ~v21)
      (aset 6 ~v02)
      (aset 7 ~v12)
      (aset 8 ~v22))))

(defmacro mat4
  ([] `(mat4 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1))
  ([v00 v10 v20 v30 v01 v11 v21 v31 v02 v12 v22 v32 v03 v13 v23 v33]
   `(doto (js/Float32Array. 16)
      (aset 0 ~v00)
      (aset 1 ~v10)
      (aset 2 ~v20)
      (aset 3 ~v30)
      (aset 4 ~v01)
      (aset 5 ~v11)
      (aset 6 ~v21)
      (aset 7 ~v31)
      (aset 8 ~v02)
      (aset 9 ~v12)
      (aset 10 ~v22)
      (aset 11 ~v32)
      (aset 12 ~v03)
      (aset 13 ~v13)
      (aset 14 ~v23)
      (aset 15 ~v33))))
