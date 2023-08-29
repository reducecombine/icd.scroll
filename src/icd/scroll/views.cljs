(ns icd.scroll.views
  (:require
   [icd.scroll.events :as events]
   [icd.scroll.subs :as subs]
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn title []
  (let [name (subscribe [::subs/name])]
    [re-com/title
     :src   (at)
     :label (str "Hello from " @name)
     :level :level1]))

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

(defn main-panel []
  [re-com/v-box
   :src      (at)
   :height   "100%"
   :children [[title]
              [form]]])
