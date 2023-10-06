(ns icd.scroll.dev
  (:require
   [clojure.tools.namespace.parse]
   [clojure.tools.namespace.repl :refer [set-refresh-dirs]]
   [formatting-stack.project-formatter]
   [formatting-stack.strategies.impl]
   [formatting-stack.util]))

(set-refresh-dirs "dev" "src" "test")

;; bring in https://github.com/nedap/formatting-stack/pull/212
(alter-var-root #'formatting-stack.strategies.impl/filename->ns
                (constantly (fn [^String filename]
                              (when-not (-> filename (.endsWith ".cljs"))
                                (some-> filename
                                        formatting-stack.util/read-ns-decl
                                        clojure.tools.namespace.parse/name-from-ns-decl
                                        formatting-stack.strategies.impl/safe-the-ns)))))

(defn format!
  {:shadow.build/stage :compile-prepare}
  [state]
  ;; Disabled for now:
  #_(formatting-stack.project-formatter/format-and-lint-project! :in-background? false)
  ;; must be returned:
  state)
