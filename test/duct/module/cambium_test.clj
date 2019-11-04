(ns duct.module.cambium-test
  (:require [clojure.test :as t]
            [duct.core :as duct]
            [duct.module.cambium :as sut]))

(duct/load-hierarchy)

(t/deftest module-test
  (let [config {:duct.profile/base
                {:duct.core/project-ns 'some-api}
                :duct.profile/prod {}
                :duct.module/cambium {}}]
    (t/is (= {:duct.core/project-ns 'some-api
              :duct.core/environment :production
              :duct.logger/cambium {}}
             (duct/prep-config config)))))
