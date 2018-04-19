(ns hawaii-quiz.core
  (:require
   [reagent.core :as r]))

(def island-names
  ["NIIHAU" "KAUAI" "OAHU" "MOLOKAI" "LANAI" "MAUI" "HAWAII" "KAHOOLAWE"])


(def initial-state (r/atom {:correct-answers 0
                            :wrong-answers 0
                            :seconds 0
                            :selected-island island-names}))

(defn random-island []
  (take 1 (shuffle island-names)))

;; -------------------------
;; Views
(defn score-board []
  [:div
   [:h3 "Welcome to Pick An Island!"]
   [:button "Start/Reset"]
   [:p "When the timer starts, click the name that corresponds to the island with a red border"]])

(defn island [name]
  (let [normal-name (clojure.string/lower-case name) ]
    [:div {:class normal-name}
     [:img {:src (str "img/" normal-name ".svg")}]]))

(defn islands [selected-island]
  (map (fn [name] ^{:key name} [island name])
       island-names))

(defn button-list []
  [:ul
   (map (fn [item] ^{:key item} [:li [:button item]])
        island-names)])

(defn home-page []
  [:div.App
   [:div.ocean
    (let [{:keys [selected-island]} @initial-state]
      (islands selected-island))]

   [:div.dashboard
    [score-board]
    [button-list]]])
;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
