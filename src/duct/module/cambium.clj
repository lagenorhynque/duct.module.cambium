(ns duct.module.cambium
  (:require [duct.core :as duct]
            [integrant.core :as ig]))

(defmethod ig/init-key :duct.module/cambium
  [_ _]
  (fn [config]
    (duct/merge-configs
     config
     {:duct.logger/cambium {}})))
