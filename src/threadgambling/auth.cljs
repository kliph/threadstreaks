(ns threadgambling.auth
  (:require [threadgambling.state :as s]))

(set! *warn-on-infer* true)

(defn on-sign-in [^js/gapi.auth2.GoogleUser google-user]
  (let [^js/gapi.auth2.BasicProfile profile (.getBasicProfile google-user)]
    (js/console.log (str "ID: " (.getId profile)))
    (js/console.log (str "Name: " (.getName profile)))
    (js/console.log (str "Email: " (.getEmail profile)))))


(defn on-failure [error]
  (js/console.log error))


(defn ^:export render-button []
  (.render js/gapi.signin2
           "google-signin"
           #js {"scope" "profile email"
                "onsuccess" on-sign-in
                "onfailure" on-failure}))

(defn onSignOut []
  (let [^js/gapi.auth2.GoogleAuth auth2 (.getAuthInstance js/gapi.auth2)
        ^js/Promise sign-out (.signOut auth2)]
    (.then sign-out
           (fn [] (js/console.log "User Signed out")))))

(defn sign-in-page []
  [:div#sign-in-page
   ;; [:a {:on-click #(swap! s/app-state assoc
   ;;                        :signed-in true)
   ;;      :href "/#/sign-in"}]

   [:div {:id "google-signin"}]
   [:a {:on-click (fn [] (onSignOut))} "Sign out"]])
