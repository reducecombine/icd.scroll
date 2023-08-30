(ns icd.scroll.views
  (:require
   [icd.scroll.events :as events]
   [icd.scroll.letters :as letters]
   [re-com.core :as re-com :refer [at]]
   [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn title []
  [re-com/title
   :src (at)
   :class "app-title"
   :label "Letter viewer"
   :level :level2
   :parts {:wrapper {:style {:align-items "center"}}}])

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

(defn controls []
  (let [empty-stack? (subscribe [::letters/empty-stack?])]
    [re-com/h-box
     :src      (at)
     :padding "0 40px 20px"
     :children [[selector]
                [:div {:style {:flex-grow "1"}}]
                (when-not @empty-stack?
                  [:button {:style {:border "solid 1px #e8e8e8"
                                    :height "23px"}
                            :on-click (fn [_]
                                        (dispatch [::events/back]))}
                   "Back"])]]))

(defn current-letter []
  (let [{:keys [::letters/id ::letters/content ::letters/title]} @(subscribe [::letters/selected-letter])]
    (dispatch [::events/reconcile-scroll-position-in-dom! id])
    [re-com/v-box :children [[re-com/title
                              :src (at)
                              :label title
                              :level :level2
                              :parts {:wrapper {:class "letter-title"}}]
                             [:div.letter-window {:data-letter-id (str id)
                                                  :on-scroll (fn [e]
                                                               (dispatch [::events/update-scroll-position-in-db id (-> e .-target .-scrollTop)]))}
                              [:div content]]]]))

(defn main-panel []
  [re-com/v-box
   :src      (at)
   :children [[title]
              [re-com/gap :size "30px"]
              [controls]
              [current-letter]]])
