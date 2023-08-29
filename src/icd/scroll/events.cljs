(ns icd.scroll.events
  (:require
   [icd.scroll.db :as db]
   [icd.scroll.letters :as letters]
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db ::initialize-db
                       (fn [_db _args]
                         db/default-db))

(re-frame/reg-event-db ::select-letter
                       (fn [db [_ letter]]
                         (assoc db
                                ::letters/selected-letter letter)))
