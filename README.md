# clj-gatling-example

Example project on how to create performance tests using [clj-gatling](https://github.com/mhjort/clj-gatling)

The tests are run against simple demo server running in Heroku.
This server is only for demo purpose and cannot handle heavy load.
Please, use this only for testing with small number of parallel users-

## Usage

  $ lein run [SIMULATION] [NUMBER_OF_SIMULTANEOUS_USERS] [NUMBER_OF_REQUESTS]

  # e.g. lein run metrics 50 100

## License

Copyright (C) 2014 Markus Hjort

Distributed under the Eclipse Public License, the same as Clojure.
