(defproject duct.module.cambium "0.1.0"
  :description "Duct module for Cambium"
  :url "https://github.com/lagenorhynque/duct.module.cambium"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :min-lein-version "2.8.1"
  :dependencies [;; Duct
                 [duct/core "0.7.0"]
                 [duct/logger "0.3.0"]

                 ;; Cambium
                 [cambium/cambium.codec-cheshire "0.9.3"]
                 [cambium/cambium.core "0.9.3"]
                 [cambium/cambium.logback.json "0.4.3"]]
  :deploy-repositories [["releases" :clojars]]
  :profiles {:dev {:resource-paths ["dev/resources"]
                   :dependencies [[org.clojure/clojure "1.10.1"]
                                  [pjstadig/humane-test-output "0.10.0"]]
                   :plugins [[jonase/eastwood "0.3.6"]
                             [lein-cljfmt "0.6.4"]
                             [lein-cloverage "1.1.2"]
                             [lein-kibit "0.1.7"]]
                   :aliases {"test-coverage" ^{:doc "Execute cloverage."}
                             ["with-profile" "test" "cloverage" "--codecov" "--junit"]
                             "lint" ^{:doc "Execute cljfmt check, eastwood and kibit."}
                             ["do" ["cljfmt" "check"] ["eastwood"] ["kibit"]]}
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]}})
