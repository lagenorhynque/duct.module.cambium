(ns duct.module.cambium-test
  (:require [clojure.test :as t]
            [duct.core :as duct]
            [duct.module.cambium :as sut]))

(duct/load-hierarchy)

(t/deftest module-test
  (t/testing "without :top-level-field option"
    (let [config {:duct.profile/base
                  {:duct.core/project-ns 'some-api}
                  :duct.profile/prod {}
                  :duct.module/cambium {}}]
      (t/is (= {:duct.core/project-ns 'some-api
                :duct.core/environment :production
                :duct.logger/cambium {}}
               (duct/prep-config config)))))
  (t/testing "with :top-level-field option"
    (let [config {:duct.profile/base
                  {:duct.core/project-ns 'some-api}
                  :duct.profile/prod {}
                  :duct.module/cambium {:top-level-field :foo}}]
      (t/is (= {:duct.core/project-ns 'some-api
                :duct.core/environment :production
                :duct.logger/cambium {:top-level-field :foo}}
               (duct/prep-config config))))))
