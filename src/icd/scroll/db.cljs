(ns icd.scroll.db
  (:require [icd.scroll.letters :as letters]))

(def default-db
  (let [selected (first letters/all)]
    {::letters/selected-letter selected
     ::letters/scroll-positions {}
     ::letters/stack            [selected]}))
