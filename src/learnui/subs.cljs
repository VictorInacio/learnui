(ns learnui.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  ::count
  (fn [db]
      (:count db)))

(re-frame/reg-sub
  ::count-minus-ten
  (fn [db]
      (- (:count db) 10)))
