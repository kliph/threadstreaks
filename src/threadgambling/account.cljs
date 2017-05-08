(ns threadgambling.account
  (:require [threadgambling.state :as s]
            [reagent.session :as session]
            [threadgambling.color :as c]
            [cljs-react-material-ui.core :as ui]))

(defn atom-input [props]
  (let [{:keys [value-ks label disabled]} props]
    [ui/text-field {:type "text"
                    :underline-focus-style {:border-color c/purp}
                    :floating-label-focus-style {:color c/purp}
                    :floating-label-text label
                    :name label
                    :disabled disabled
                    :value (get-in @s/app-state value-ks "")
                    :on-change #(swap! s/app-state
                                       assoc-in
                                       value-ks
                                       (-> %
                                           .-target
                                           .-value))}]))

(defn update-page []
  (fn []
    (swap! s/app-state assoc :account (session/get :user))
    [:div#update-container
     [atom-input {:label "Name"
                  :value-ks [:account :name]}]
     [atom-input {:label "Email"
                  :value-ks [:account :email]
                  :disabled true}]
     [atom-input {:label "Team Name"
                  :value-ks [:account :team]}]
     [:div.spacer]
     [ui/raised-button
      {:label "Save changes"
       :href "/#/"
       :full-width true
       :className "update-button"}]]))
