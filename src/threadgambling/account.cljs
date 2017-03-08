(ns threadgambling.account
  (:require [threadgambling.state :as s]
            [cljs-react-material-ui.core :as ui]))

(def purp "#29002E")

(defn atom-input [props]
  (let [{:keys [value-ks label]} props]
    [ui/text-field {:type "text"
                    :underline-focus-style {:border-color purp}
                    :floating-label-focus-style {:color purp}
                    :floating-label-text label
                    :value (get-in @s/app-state value-ks "")
                    :on-change #(swap! s/app-state
                                       assoc-in
                                       value-ks
                                       (-> %
                                           .-target
                                           .-value))}]))

(defn update-page []
  [:div#update-container
   [atom-input {:label "Name"
                :value-ks [:account :name]}]
   [atom-input {:label "Email"
                :value-ks [:account :email]}]
   [atom-input {:label "Team Name"
                :value-ks [:account :team]}]
   [:div.spacer]
   [ui/raised-button
    {:label "Save changes"
     :full-width true
     :className "update-button"}]])
