(ns learnui.views
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [learnui.subs :as subs]
    [learnui.events :as events]
    ))

(defn simple-component [name]
      [:div
       [:p "Esse é o nome que eu apresento: " name]
       ])

(defn simple-parent [name]
      [:div
       [:p "I include simple-component."]
       [simple-component name]])


(defn hello-component [name]
      [:p "Hello, " name "!"])

(defn say-hello []
      [hello-component "Victor"])


(defn lister [items]
      [:ul
       (for [item items]
            ^{:key item} [:li "Item " item])])

(defn lister-user []
      [:div
       "Here is a list:"
       [lister (range 3)]])

(def click-count (r/atom 0))

(defn counting-component []
      [:div
       "The atom " [:code "click-count"] " has value: "
       @click-count ". "
       [:input {:type     "button" :value "Click me!"
                :on-click #(swap! click-count inc)}]])

(defn counting-component-reframe []
      (let [count (re-frame/subscribe [::subs/count])
            count-10 (re-frame/subscribe [::subs/count-minus-ten])]
           [:div
            [:p "The atom " [:code "click-count"] " has value: "
             @count ". "]
            [:p "The atom minus 10 " [:code "click-count"] " has value: "
             @count-10 ". "]
            [:input {:type     "button" :value "Click me!"
                     :on-click #(re-frame/dispatch [::events/inc-count])}]]))

(defn main-panel []
      (let [name (re-frame/subscribe [::subs/name])]
           [:div
            ;[say-hello]
            ;[simple-parent @name]
            ;[lister-user]
            [:input {:type     "button" :value "I am a new red button!"
                     :on-click #(re-frame/dispatch [::events/inc-count])}]
            [counting-component]
            [counting-component-reframe]
            ]))
