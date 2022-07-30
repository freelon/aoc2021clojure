(ns aoc2021.day02
  (:require [clojure.string :as string]))

(def test-input "forward 5
down 5
forward 8
up 3
down 8
forward 2
")

(def input (slurp "resources/day02.txt"))

(defn make-delta-position [[command value]]
  (let [v (Long/parseLong value)]
    (case command
      "down" {:x 0 :y v}
      "up" {:x 0 :y (- v)}
      "forward" {:x v :y 0})))

(defn add-positions [left right]
  {:x (+ (get left :x) (get right :x))
   :y (+ (get left :y) (get right :y))})


;; part 1
(->> (string/split-lines input)
     (map #(string/split % #" "))
     (map make-delta-position)
     (reduce add-positions {:x 0 :y 0}))
(let [foo *1]
  (* (get foo :x) (get foo :y))) 

     