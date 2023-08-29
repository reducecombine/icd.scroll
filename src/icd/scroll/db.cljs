(ns icd.scroll.db
  (:require [icd.scroll.letters :as letters]))

(def default-db
  {::letters/selected-letter  (first letters/all)
   ::letters/scroll-positions {}
   ::letters/stack            []})
