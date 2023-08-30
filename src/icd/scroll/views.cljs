(ns icd.scroll.views
  (:require
   [icd.scroll.events :as events]
   [icd.scroll.letters :as letters]
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn title []
  [re-com/title :src (at)
   :label "Letter viewer"
   :level :level1])

(defn controls []
  (let [empty-stack? (subscribe [::letters/empty-stack?])]
    [:button {:disabled @empty-stack?
              :on-click (fn [_]
                          (dispatch [::events/back]))}
     "Back"]))

(defn selector []
  (let [selected (subscribe [::letters/selected-letter])]
    (into [:ul]
          (mapv (fn [{:keys [::letters/id ::letters/title] :as current}]
                  (let [selected? (= id (::letters/id @selected))]
                    ^{:key title} [:li.letter-selector {:class (when selected?
                                                                 "selected")}
                                   [:a {:on-click (fn [_]
                                                    (when-not selected?
                                                      (dispatch [::events/select-letter current])))}
                                    title]]))
                letters/all))))

(defn current-letter []
  (let [{:keys [::letters/id ::letters/content] :as selected} @(subscribe [::letters/selected-letter])]
    (dispatch [::events/reconcile-scroll-position-in-dom! id])
    [:div.letter-window {:data-letter-id (str id)
                         :on-scroll (fn [e]
                                      (dispatch [::events/update-scroll-position-in-db id (-> e .-target .-scrollTop)]))}
     content]))

(defn main-panel []
  [re-com/v-box
   :src      (at)
   :height   "100%"
   :children [[title]
              [controls]
              [selector]
              [current-letter]]])
