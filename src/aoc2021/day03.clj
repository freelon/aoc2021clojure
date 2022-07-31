(ns aoc2021.day03
  (:require [clojure.string :as string]))

(def test-input "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010
")

(def input (slurp "resources/day03.txt"))

;; part 1
(defn- count-bits [counts [index bit]] 
  (let [current-bit-count (get counts index 0)
        to-add (if (= bit \1) 1 0)
        new-bit-count (+ current-bit-count to-add)]
    (assoc counts index new-bit-count)))

(defn- count-line [counts line]
  (reduce count-bits
          counts
          (map-indexed vector (char-array line))))

(let [lines (string/split-lines input)
      line-count (count lines)
      m (reduce count-line {} lines)
      f (fn [it] (if (< (/ line-count 2) it)
                   \1
                   \0))
      gamma-s (map f (map #(m %) (sort (keys m))))
      epsilon-s (map #(if (= \1 %) \0 \1) gamma-s)
      gamma (Long/parseLong (string/join gamma-s) 2)
      epsilon (Long/parseLong (string/join epsilon-s) 2)
      power-consumption (* gamma epsilon)] 
  (println "gamma" gamma)
  (println "epsilon" epsilon)
  (println "power consumption" power-consumption))