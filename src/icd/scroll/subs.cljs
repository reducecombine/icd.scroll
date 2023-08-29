(ns icd.scroll.subs
  (:require
   [icd.scroll.letters :as letters]
   [re-frame.core :as re-frame]))

(re-frame/reg-sub ::letters/selected-letter ::letters/selected-letter)

(re-frame/reg-sub ::letters/stack ::letters/stack)
