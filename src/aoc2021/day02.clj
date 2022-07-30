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
  {:x (+ (left :x) (right :x))
   :y (+ (left :y) (right :y))})


;; part 1
(let [coordinate (->> (string/split-lines input)
                      (map #(string/split % #" "))
                      (map make-delta-position)
                      (reduce add-positions {:x 0 :y 0}))]
  (* (coordinate :x) (coordinate :y)))

(defn drive [old [command value]]
  (case command
    "down"   (update old :aim #(+ % value))
    "up"     (update old :aim #(- % value))
    "forward"   {:x (+ value (old :x))
                 :y (+
                     (*    value (old :aim))
                     (old :y))
                 :aim (old :aim)}))


;; part 2
(let [coordinate (->> (string/split-lines test-input)
                      (map #(string/split % #" "))
                      (map #(vec [(first %) (Long/parseLong (second %))]))
                      (reduce drive {:x 0 :y 0 :aim 0}))]
  (* (coordinate :x) (coordinate :y)))

