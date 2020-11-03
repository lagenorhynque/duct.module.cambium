(ns duct.module.cambium
  (:require
   [duct.core :as duct]
   [integrant.core :as ig]))

(defmethod ig/init-key :duct.module/cambium
  [_ options]
  (fn [config]
    (duct/merge-configs
     config
     {:duct.logger/cambium (select-keys options [:top-level-field])})))
