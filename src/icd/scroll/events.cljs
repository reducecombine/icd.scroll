(ns icd.scroll.events
  (:require
   [icd.scroll.db :as db]
   [icd.scroll.letters :as letters]
   [re-frame.core :refer [inject-cofx reg-cofx reg-event-db reg-event-fx]]))

(defn initialize-db [_db _args]
  db/default-db)

(reg-event-db ::initialize-db initialize-db)

(defn back [db [_]]
  (let [db-after (update db ::letters/stack pop)
        new-item (-> db-after ::letters/stack peek)]
    (-> db-after
        (assoc ::letters/selected-letter new-item))))

(reg-event-db ::back back)

(defn select-letter [db [_ letter]]
  (-> db
      (assoc  ::letters/selected-letter letter)
      (update ::letters/stack conj letter)))

(reg-event-db ::select-letter select-letter)

(defn update-scroll-position-in-db [db [_ selected-id scroll-top-px]]
  (assoc-in db [::letters/scroll-positions selected-id] scroll-top-px))

(reg-event-db ::update-scroll-position-in-db update-scroll-position-in-db)

(defn query-selector [cofx _]
  (assoc cofx ::query-selector (fn [query]
                                 (js/document.querySelector query))))

(reg-cofx ::query-selector query-selector)

(defn reconcile-scroll-position-in-dom! [{:keys [db ::query-selector]} [_ selected-id]]
  (let [db-scroll-top (get-in db [::letters/scroll-positions selected-id] 0)
        dom (query-selector (str ".letter-window[data-letter-id=\"" selected-id "\"]"))
        dom-scroll-top (or (some-> dom .-scrollTop)
                           0)]
    (when-not (= db-scroll-top dom-scroll-top)
      (when dom
        (set! (.-scrollTop dom) db-scroll-top)))
    {:db db}))

(reg-event-fx ::reconcile-scroll-position-in-dom!
              [(inject-cofx ::query-selector)]
              reconcile-scroll-position-in-dom!)
