.PHONY: clean run kondo
.DEFAULT_GOAL := run

clean:
	rm -rf resources/public/js/compiled

.make_npm: package.json
	npm install --no-fund --no-audit --no-progress --loglevel=error
	touch $@

run: clean .make_npm kondo
	open "http://localhost:3000"
	npm run shadow-cljs watch app

.make_kondo_prep: deps.edn
	clojure -M:kondo --lint $$(clojure -Spath) --dependencies --parallel --copy-configs > $@

kondo: .make_kondo_prep
	clojure -M:kondo --lint src
