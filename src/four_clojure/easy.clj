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

;; 28 Flatten a Sequence
(fn [c]
  (letfn [(f [x y]
            (reduce (fn [m n] (if (sequential? n) (f m n) (conj m n))) x y))]
    (f [] c)))

;; 29 Get the Caps
(fn [s] (apply str (filter #(< 64 (int %) 91) s)))

;; 30 Compress a Sequence
(fn [c] (reduce (fn [s x] (if (= (last s) x) s (conj s x))) [] c))

;; 31 Pack a Sequence
(fn [c]
  (letfn [(f [s x] (if (= x (-> s last last)) (conj (pop s) (conj (last s) x)) (conj s [x])))]
    (reduce f [] c)))

;; 32 Duplicate a Sequence
(fn [c] (reduce (fn [s x] (conj s x x)) [] c))

;; 33 Replicate a Sequence
(fn [c n] (reduce (fn [s x] (into s (repeat n x))) [] c))

;; 34 Implement range
(fn f [i x] (if (< i x) (into (f i (dec x)) [(dec x)]) []))

;; 38 Maximum value
(fn [x & xs] (reduce #(if (< %1 %2) %2 %1) x xs))

;; 39 Interleave Two Seqs
(fn [a b] (flatten (map #(identity [%1 %2]) a b)))

;; 40 Interpose a Seq
(fn [x c] (reduce (fn [s y] (conj s x y)) [(first c)] (rest c)))

;; 41 Drop Every Nth Item
(fn [c n] (for [i (range (count c)) :when (not= (rem (+ i 1) n) 0)] (nth c i)))

;; 42 Factorial Fun
(fn [n] (apply * (range 1 (+ n 1))))

;; 45 Intro to Iterate
(list 1 4 7 10 13)

;; 47 Contain Yourself
4

;; 48 Intro to some
6

;; 49 Split a sequence
(fn [n coll] [(take n coll) (drop n coll)])

;; 51 Advanced Destructuring
[1 2 3 4 5]

;; 61 Map Construction
(fn [a b] (into {} (map vector a b)))

;; 62 Re-implement Iterate
(fn iter [f x] (lazy-seq (cons x (iter f (f x)))))

;; 63 Group a Sequence
(fn [f coll]
  (reduce (fn [m v] (let [k (f v)] (assoc m k (conj (get m k []) v)))) {} coll))

;; 66 Greatest Common Divisor
(fn [a b] (if (zero? a) b (recur (mod b a) a)))

;; 100 Least Common Multiple
(fn [x y & args]
  (let [gcd (fn [a b] (if (zero? a) b (recur (mod b a) a)))
        [a b] (reduce (fn [[a b] v] [(* a v) (gcd b v)]) [(* x y) (gcd x y)] args)]
    (/ a b)))