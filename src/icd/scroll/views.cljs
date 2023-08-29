(ns icd.scroll.views
  (:require
   [icd.scroll.events :as events]
   [icd.scroll.letters :as letters]
   [icd.scroll.subs :as subs]
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn title []
  [re-com/title :src (at)
   :label "Letter viewer"
   :level :level1])

(defn form []
  (let [model (subscribe [::subs/name])]
    ;; works but does not accept on-key-up:
    #_ [re-com/input-text :model model ,,,]
    [:input
     {:type      "text"
      :value     @model
      :on-change (fn [event]
                   (dispatch [::events/change-name (-> event .-target .-value)]))
      :on-key-up (fn [_event]
                   (dispatch [::events/change-name @model]))}]))

(defn controls []
  ;; back button
  )

(defn selector []
  (let [selected (subscribe [::letters/selected-id])]
    (into [:ul]
          (mapv (fn [{:keys [::letters/content ::letters/title]}]
                  ^{:key title} [:li.letter-selector {:class (when (= title @selected)
                                                               "selected")}
                                 [:a {:on-click (fn [_]
                                                  (dispatch [::events/select-letter title content]))}
                                  title]])
                letters/all))))

(defn current-letter []
  (let [selected (subscribe [::letters/selected-content])]
    [:div.letter-window
     @selected]))

(defn main-panel []
  [re-com/v-box
   :src      (at)
   :height   "100%"
   :children [[title]
              #_[form]
              [controls]
              [selector]
              [current-letter]]])
