(ns duct.logger.cambium
  (:require
   [cambium.codec]
   [cambium.core]
   [cambium.logback.json.flat-layout]
   [clojure.string :as str]
   [duct.logger :as logger]
   [integrant.core :as ig]))

(cambium.logback.json.flat-layout/set-decoder!
 cambium.codec/destringify-val)

(defmacro ^:private deflevel [sym]
  (let [log-macro (symbol "cambium.core" (name sym))
        doc-str (str "Output " (str/upper-case sym) " log.\n"
                     "  `mdc` map will be spliced into top-level fields of the JSON log output.")
        arglists ''([msg-or-throwable] [msg mdc-or-throwable] [msg mdc throwable])]
    `(defmacro ~sym
       ~doc-str
       {:arglists ~arglists}
       ([msg-or-throwable#]
        (with-meta `(~'~log-macro ~msg-or-throwable#) ~'(meta &form)))
       ([msg# mdc-or-throwable#]
        (with-meta `(~'~log-macro ~mdc-or-throwable# ~msg#) ~'(meta &form)))
       ([msg# mdc# throwable#]
        (with-meta `(~'~log-macro ~mdc# ~throwable# ~msg#) ~'(meta &form))))))

(deflevel fatal)
(deflevel error)
(deflevel warn)
(deflevel info)
(deflevel debug)
(deflevel trace)

(defrecord Logger
  [config]
  logger/Logger
  (-log [_ level ns-str _ line _ event data]
    (let [mdc {:ns ns-str
               :line line
               :column nil
               (:top-level-field config) data}]
      (case level
        :fatal (fatal event mdc)
        :error (error event mdc)
        :warn (warn event mdc)
        :info (info event mdc)
        :debug (debug event mdc)
        :trace (trace event mdc)
        nil))))

(defmethod ig/init-key :duct.logger/cambium
  [_ {:keys [top-level-field]
      :or {top-level-field :option}}]
  (->Logger {:top-level-field top-level-field}))
