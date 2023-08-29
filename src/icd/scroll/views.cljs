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
  ;; back button
  )

(defn selector []
  (let [selected (subscribe [::letters/selected-letter])]
    (into [:ul]
          (mapv (fn [{:keys [::letters/id ::letters/title] :as current}]
                  ^{:key title} [:li.letter-selector {:class (when (= id (::letters/id @selected))
                                                               "selected")}
                                 [:a {:on-click (fn [_]
                                                  (dispatch [::events/select-letter current]))}
                                  title]])
                letters/all))))

(defn current-letter []
  (let [selected (subscribe [::letters/selected-letter])]
    [:div.letter-window
     (::letters/content @selected)]))

(defn main-panel []
  [re-com/v-box
   :src      (at)
   :height   "100%"
   :children [[title]
              [controls]
              [selector]
              [current-letter]]])
