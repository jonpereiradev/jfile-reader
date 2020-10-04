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
- [x] Migrating documentation to Github Wiki
- [x] Refactory JFileLine.getRow() to JFileLine.getLineNumber()
- [x] Refactory JFileColumn.getPosition() to JFileColumn.getColumnNumber()
- [x] Refactory JFileLine to LineValue
- [x] Refactory JFileColumn to ColumnValue
- [x] Moving JFileReader validate() to JFileValidator class
- [X] Refactory Report to ValidatorReport
- [X] Refactory Report isInvalid method to isNotValid
- [X] Refactory ReaderConfiguration to JFileReaderConfig
- [X] Removing internal complexities
- [X] Add throws IOException in some methods of JFileReaderFactory
- [x] MIT License
- [x] Removing exception suppress from ColumnValue getter methods
- [] Enrich the API documentation

**Version:** 0.7.0

- [x] Inclusion of method canValidate for Rules
- [x] LocalDate and LocalDateTime validation Rules
- [x] Benchmark implementation with JMH

## License

JFile-Reader is available under the [MIT license](https://tldrlegal.com/license/mit-license).
