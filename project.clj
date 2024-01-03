(defproject duct.module.cambium "1.3.2-SNAPSHOT"
  :description "Duct module for Cambium"
  :url "https://github.com/lagenorhynque/duct.module.cambium"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :min-lein-version "2.8.1"
  :dependencies [;; Duct
                 [duct/core "0.8.0"]
                 [duct/logger "0.3.0"]

                 ;; Cambium
                 [cambium/cambium.codec-cheshire "1.0.0"]
                 [cambium/cambium.core "1.1.1"]
                 [cambium/cambium.logback.json "0.4.6"]]
  :deploy-repositories [["releases" :clojars]]
  :profiles {:dev {:resource-paths ["dev/resources"]
                   :dependencies [[org.clojure/clojure "1.11.1"]
                                  [pjstadig/humane-test-output "0.11.0"]]
                   :plugins [[jonase/eastwood "1.4.2"]
                             [lein-cloverage "1.2.4"]
                             [lein-kibit "0.1.8"]]
                   :aliases {"test-coverage" ^{:doc "Execute cloverage."}
                             ["with-profile" "test" "cloverage" "--codecov" "--junit"]
                             "lint" ^{:doc "Execute eastwood and kibit."}
                             ["do" ["eastwood"] ["kibit"]]}
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]}})
