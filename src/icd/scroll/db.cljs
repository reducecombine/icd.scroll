(ns icd.scroll.db
  (:require [icd.scroll.letters :as letters]))

(def default-db
  {::letters/selected-id      (-> letters/all first ::letters/title)
   ::letters/selected-content (-> letters/all first ::letters/content)
   ::letters/stack            []})
