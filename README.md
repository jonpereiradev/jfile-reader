# JFile Reader

This project provides an easy use and extensible API for read, validate, and parse files into java objects.

## Installation

```xml
<dependency>
  <groupId>com.github.jonpereiradev</groupId>
  <artifactId>jfile-reader</artifactId>
  <version>${jfile-reader.version}</version>
</dependency>
```

## Release Note

**Version:** 0.8.0

- [x] Removing commons-lang dependency
- [x] Refactory JFileLine.getRow() method to JFileLine.getLineNumber() method
- [x] Refactory JFileColumn.getPosition() method to JFileColumn.getColumnNumber() method
- [x] Refactory JFileLine class to LineValue class
- [x] Refactory JFileColumn class to ColumnValue class
- [x] Moving JFileReader validate() method to JFileValidator class
- [X] Refactory Report class to ValidationReport class
- [X] Refactory Report isInvalid method to isNotValid method
- [X] Refactory ReaderConfiguration class to JFileReaderConfig class
- [X] Add throws IOException in JFileReaderFactory methods
- [x] Removing exception suppress from ColumnValue getter methods
- [X] Removing internal complexities
- [x] Enrich the API documentation
- [x] MIT License

**Version:** 0.7.0

- [x] Inclusion of method canValidate for Rules
- [x] LocalDate and LocalDateTime validation Rules
- [x] Benchmark implementation with JMH

## License

JFile-Reader is available under the [MIT license](https://tldrlegal.com/license/mit-license).
