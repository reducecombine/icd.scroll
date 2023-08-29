(ns icd.scroll.subs
  (:require
   [icd.scroll.letters :as letters]
   [re-frame.core :as re-frame]))

(re-frame/reg-sub ::letters/selected-id ::letters/selected-id)

(re-frame/reg-sub ::letters/selected-content ::letters/selected-content)

(re-frame/reg-sub ::letters/stack ::letters/stack)
