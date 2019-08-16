(ns four-clojure.medium)

;; 43 Reverse Interleave
(fn [coll n] (apply map list (partition n coll)))

;; 44 Rotate Sequence
(fn [i coll] (let [n (mod i (count coll))] (concat (drop n coll) (take n coll))))

;; 46 Flipping out
(fn [f](fn [a b](f b a)))

;; 50 Split by type
(fn [coll] (vals (reduce (fn [m v] (assoc m (type v) (concat (get m (type v)) [v]))) {} coll)))

;; 54 Partition a Sequence
(fn [n coll]
  (loop [s [] c coll]
    (if (< (count c) n)
      s
      (recur (conj s (take n c)) (drop n c)))))

;; 55 Count Occurrences
(fn [coll] (reduce (fn [m v] (assoc m v (inc (get m v 0)))) {} coll))

;; 56 Find Distinct Items
(fn [coll] (reduce (fn [s v] (if (every? #(not= v %) s) (conj s v) s)) [] coll))

;; 58 Function Composition
(fn f-seq
  ([f] f)
  ([f g] (fn
           ([] (f (g)))
           ([x] (f (g x)))
           ([x y] (f (g x y)))
           ([x y & z] (f (apply g x y z)))))
  ([f g & h] (reduce f-seq (list* f g h))))

;; 59 Juxtaposition
(fn [& f] (fn [& args] (map #(apply % args) f)))

;; 60 Sequence Reductions
(defn seq-reduce
  ([f s] (seq-reduce f (f (first s)) (rest s)))
  ([f x s] (lazy-seq
             (if (empty? s)
               [x]
               (cons x (seq-reduce f (f x (first s)) (rest s)))))))

;; 65 Black Box Testing
(fn [x]
  (let [t (empty x)]
    (cond (= t {}) :map
          (= t #{}) :set
          (= t '()) (if (reversible? t) :vector :list))))

;; 67 Prime Numbers
(fn nth-prime [n]
  (case n
    1 [2]
    2 [2 3]
    3 [2 3 5]
    4 [2 3 5 7]
    5 [2 3 5 7 11]
    6 [2 3 5 7 11 13]
    (letfn [(is-prime? [x coll]
              (loop [i 0]
                (if (and (< i (count coll)) (< (coll i) (/ x 2)))
                  (if (zero? (mod x (coll i))) false (recur (inc i)))
                  true)))]
      (let [prev (nth-prime (dec n))]
        (conj prev (loop [i (+ (last prev) 2)]
                     (if (is-prime? i prev) i (recur (+ i 2)))))))))


