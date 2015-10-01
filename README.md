# Spring REST Sample

This is a sample application demonstrating a RESTfull application developed in Spring Framework.

## Technology stack

- Dependency injection: Spring 4.2.1.RELEASE
- REST controller: Spring Web MVC 4.2.1.RELEASE
- JSON conversion: Jackson 2.4.6
- Validation: Hibernate Validator 5.1.3.Final

## Running locally

To start the web application execute

	mvn

This will cleanly build the application, start a Jetty container and deploy the application into it.

You can then access the application at [http://localhost:8080/](http://localhost:8080/).

## Full build with integration tests

To perform full build of the application including execution of the integration tests execute

	mvn -Pit

This will cleanly build the application, start a Jetty container, deploy the application into it,
execute all the integration tests and shutdown the Jetty container.
