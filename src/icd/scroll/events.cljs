(ns icd.scroll.events
  (:require
   [icd.scroll.db :as db]
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db ::initialize-db
                       (fn [_db _args]
                         db/default-db))

(re-frame/reg-event-db ::change-name
                       (fn [db [_ name]]
                         (assoc db :name name)))
