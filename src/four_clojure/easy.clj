(ns four-clojure.easy
  (:gen-class))

;; 19 Last Element
(fn [s] (if (next s) (recur (next s)) (first s)))

;; 20 Penultimate Element
(fn [c] (let [n (count c)] (when (< 1 n) (nth c (- n 2)))))

;; 21 Nth Element
(fn [s i](first (drop i s)))

;; 22 Count a Sequence
(fn f [s] (if (empty? s) 0 (+ 1 (f (next s)))))

;; 23 Reverse a Sequence
(fn [s] (reduce conj '() s))

;; 24 Sum It All Up
(fn [v] (apply + v))

;; 25 Find the odd numbers
(fn [s] (filter odd? s))

;; 26 Fibonacci Sequence
(fn [n] (map (fn f [i] (if (< i 2) 1 (+ (f (- i 1)) (f (- i 2))))) (range n)))

;; 27 Palindrome Detector
(fn [s] (= (seq s) (reverse s)))