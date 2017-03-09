(ns threadgambling.standings
  (:require [threadgambling.state :as s]))

(defn standings-row [props]
  (let [{:keys [rank team user points streak current-pick]} props]
    [:tr.standings
     [:td rank]
     [:td [:a {:on-click #(swap! s/app-state assoc :page :team)} team]]
     [:td user]
     [:td points]
     [:td streak]
     [:td current-pick]]))

(defn standings-page []
  [:div.standings
   [:h2 "Standings"]
   [:table
    {:cell-spacing "0" :width "100%"}
    [:thead>tr
     [:th]
     [:th "Team"]
     [:th "User"]
     [:th "Points"]
     [:th "Streak of"]
     [:th "Current Pick"]]
    [:tbody
     (map (fn [x] ^{:key (str (:rank x) (:team x) (:user x))} [standings-row x])
          (sort-by :rank (@s/app-state :standings)))]]])
