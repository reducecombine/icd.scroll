(ns icd.scroll.events
  (:require
   [icd.scroll.db :as db]
   [icd.scroll.letters :as letters]
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db ::initialize-db
                       (fn [_db _args]
                         db/default-db))

(re-frame/reg-event-db ::back
                       (fn [db [_]]
                         (let [db-after (update db ::letters/stack pop)
                               new-item (-> db-after ::letters/stack peek)]
                           (-> db-after
                               (assoc  ::letters/selected-letter new-item)))))

(re-frame/reg-event-db ::select-letter
                       (fn [db [_ letter]]
                         (-> db
                             (assoc  ::letters/selected-letter letter)
                             (update ::letters/stack conj letter))))
