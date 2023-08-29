(ns icd.scroll.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub ::name
                  ;; a fn of DB -> value:
                  :name)
