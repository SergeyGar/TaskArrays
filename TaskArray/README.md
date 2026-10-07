# Java array processing

Requires JDK 17 or newer and Maven 3.9 or newer. Run commands from the project root.

```shell
mvn clean verify
mvn exec:java
mvn exec:java -Dexec.args="data/arrays.txt"
```

The default input is `data/arrays.txt`. Custom input paths must be relative.
UTF-8 lines contain 32-bit signed integers separated by commas, semicolons,
whitespace, or hyphens. For example:

```text
1, 2, 3
1 - 2 - 3
3 4 7
-5, 0, -2
```

A hyphen between values acts as a delimiter; a minus at the start of a token
acts as its sign. Use commas or semicolons for unambiguous negative values.
Empty lines are valid empty definitions for the parser and are skipped by the launcher.
Malformed and out-of-range lines are logged at WARN and skipped.
Unreadable files are logged at ERROR and cause an unsuccessful exit.

Entities defensively copy arrays. Production creation uses the `ArrayFactory`
interface and `IntegerArrayBuilder`; public constructors are also available,
as authorized, and tests use them for fixtures.
Statistics return empty optionals for empty arrays. Sums use `OptionalLong`
to avoid integer overflow. Bubble sort and insertion sort implement
`SortService` and return new entities without mutating their inputs.

Source packages separate entities, factory, builder, services, validator,
reader, parser, exception, and launcher. Every service implementation has
an interface in the service package.

Log4J2 configuration lives in `config/log4j2.xml`; Maven copies it to the runtime
classpath. Logging writes to STDOUT and `logs/app.log`.
Data files live in `data/`, and test fixtures live in `test-data/`.
Maven also copies test fixtures to the test classpath. Tests use JUnit 5.

To publish the implementation to the configured Git remote:

```shell
git push origin HEAD
```
