# clj-gatling-example

Example project on how to create performance tests using [clj-gatling](https://github.com/mhjort/clj-gatling)
The tests are run against local [SpringPetClinic](https://github.com/spring-projects/spring-petclinic) app.

## Running petclinic locally
```
 	git clone https://github.com/SpringSource/spring-petclinic.git
	mvn tomcat7:run
```

## Usage

  $ lein run [NUMBER_OF_SIMULTANEOUS_USERS] [NUMBER_OF_ROUNDS] # e.g. lein run 50 2

## License

Copyright (C) 2014 Markus Hjort

Distributed under the Eclipse Public License, the same as Clojure.
