(ns icd.scroll.events-test
  (:require
   [cljs.test :refer-macros [deftest is]]
   [icd.scroll.db :as db]
   [icd.scroll.events :as sut]
   [icd.scroll.letters :as letters]))

(deftest reconcile-scroll-position-in-dom!
  (let [id 42
        db-scroll-top 99
        dom-scroll-top 10
        dom (clj->js {:scrollTop dom-scroll-top})]
    (assert (not= db-scroll-top dom-scroll-top))
    (sut/reconcile-scroll-position-in-dom! {:db (-> db/default-db
                                                    (sut/update-scroll-position-in-db [::_ id db-scroll-top]))
                                            ::sut/query-selector (fn [q]
                                                                   dom)}
                                           [::_ id])
    (is (= db-scroll-top
           (-> dom .-scrollTop))
        "Changes the `scrollTop` DOM property, which in a browser, results in the viewport being scrolled")))
