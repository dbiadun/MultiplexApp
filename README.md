# MultiplexApp
Seat reservation system for a multiplex

If you want to use this app you need Java 8, Maven 2 and MySQL 5.x installed.
You should create `multiplexAppDb` database and add your database
username and password to `src/main/resources/application.properties`.
Your database user should have all privileges granted in `multiplexAppDb` database.
You should also set the default charset of your database to handle
polish characters properly (`ALTER DATABASE multiplexAppDb CHARACTER SET utf8 COLLATE utf8_unicode_ci;`).

In order to build and run this app, run `run.sh`.

In order to run demo run `demo.sh` (you need curl installed to do this).