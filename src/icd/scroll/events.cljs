(ns icd.scroll.events
  (:require
   [icd.scroll.db :as db]
   [icd.scroll.letters :as letters]
   [re-frame.core :refer [reg-event-db]]))

(reg-event-db ::initialize-db
              (fn [_db _args]
                db/default-db))

(reg-event-db ::back
              (fn [db [_]]
                (let [db-after (update db ::letters/stack pop)
                      new-item (-> db-after ::letters/stack peek)]
                  (-> db-after
                      (assoc  ::letters/selected-letter new-item)))))

(reg-event-db ::select-letter
              (fn [db [_ letter]]
                (-> db
                    (assoc  ::letters/selected-letter letter)
                    (update ::letters/stack conj letter))))

(reg-event-db ::update-scroll-position-in-db
              (fn [db [_ selected-id scroll-top-px]]
                (assoc-in db [::letters/scroll-positions selected-id] scroll-top-px)))

(reg-event-db ::reconcile-scroll-position-in-dom!
              (fn [db [_ selected-id]]
                (let [db-scroll-top (get-in db [::letters/scroll-positions selected-id] 0)
                      dom (js/document.querySelector (str ".letter-window[data-letter-id=\""
                                                          selected-id
                                                          "\"]"))
                      dom-scroll-top (or (some-> dom .-scrollTop)
                                         0)]
                  (when-not (= db-scroll-top dom-scroll-top)
                    (when dom
                      (set! (.-scrollTop dom) db-scroll-top)))
                  db)))
