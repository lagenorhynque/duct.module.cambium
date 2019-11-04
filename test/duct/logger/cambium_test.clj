(ns duct.logger.cambium-test
  (:require [clojure.test :as t]
            [duct.core :as duct]
            [duct.logger]
            [integrant.core :as ig]
            [duct.logger.cambium :as sut]))

(duct/load-hierarchy)

(defmacro with-system
  [[bound-var binding-expr] & body]
  `(let [~bound-var (ig/init ~binding-expr)]
     (try
       ~@body
       (finally
         (ig/halt! ~bound-var)))))

(t/deftest logger-test
  (let [config {:duct.profile/base
                {:duct.core/project-ns 'some-api}
                :duct.profile/prod {}
                :duct.module/cambium {}}]
    (with-system [sys (duct/prep-config config)]
      (let [[_ logger] (ig/find-derived-1 sys :duct/logger)]
        (doseq [level [:fatal :error :warn :info :debug :trace :undefined]]
          (duct.logger/log logger level ::some-event)
          (duct.logger/log logger level ::some-event {:detail {:value 42}}))))))
