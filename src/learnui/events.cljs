(ns learnui.events
  (:require
   [re-frame.core :as re-frame]
   [learnui.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))


(re-frame.core/reg-event-db        ;; <-- call this to register a handler
  ::inc-count                      ;; this is an event id
  (fn [db [event-name]]          ;; this function does the handling
      (prn event-name "  " db)
      (update db :count inc)))
