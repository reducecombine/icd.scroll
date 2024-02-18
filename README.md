## Letter viewer

A POC viewer for medical letters.

### Compile code and run the server

```bash
make
```

Note that the page will be offline until compilation is done.

### Connect with CIDER

* Run `make` per the previous step
* `M-x cider-connect-clj&cljs`

### Jacking in with CIDER

* Don't run `make`
* `M-x cider-jack-in-clj&cljs`
  * When it asks for a project type, select `shadow`
  * When it asks whether it should open a browser, select `y`
* As `localhost` is opened, if it warns about stale output, you probably need to wait a few seconds and then refresh the page.
