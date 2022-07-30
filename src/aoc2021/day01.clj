(ns aoc2021.day01
  (:require [clojure.string :as string]))

(def test-input "199
200
208
210
200
207
240
269
260
263
")

(def input (slurp "resources/day01.txt"))

;; part 1
(->> (string/split-lines input)
     (map #(Long/parseLong %))
     (partition 2 1)
     (filter #(< (first %) (second %)))
     (count))

;; part 2
(->> (string/split-lines input)
     (map #(Long/parseLong %))
     (partition 3 1)
     (map #(reduce + %))
     (partition 2 1)
     (filter #(< (first %) (second %)))
     (count))
