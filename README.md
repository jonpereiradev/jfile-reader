# JFile Reader

This project helps you to build functionalities that needs to read and validate files.

## Installation

```xml
<dependency>
    <groupId>com.github.jonpereiradev</groupId>
    <artifactId>jfile-reader</artifactId>
    <version>0.4.0</version>
</dependency>
```

## JFileReaderFactory

The JFileReaderFactory provides an API to read a file and instanciate the right class to handle the 
type of file:

**Example**

```java
public class Main {
    
    public static void main(String[] args) {
        String regexColumnSeparator = "\\|";
        Path path = Paths.get("path", "to", "file");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader(regexColumnSeparator);
        JFileReader jFileReader = JFileReaderFactory.newInstance(path, readerConfiguration);
    }
}
```

## Annotations

The annotations provided by the jfile reader are:

- __FileColumn:__ to map a column to a wrapper field;
- __DecimalFormatter:__ to map the pattern of a number object;
- __DateTimeFormatter:__ to map the pattern of a date object;

**Example**

```java
public class User {

    @FileColumn(1)
    private Integer type;
    
    @FileColumn(2)
    private Boolean active;
    
    @FileColumn(3)
    private String name;
    
    @FileColumn(4)
    @DecimalFormatter("#,##0.0#")
    private BigDecimal currency;
    
    @FileColumn(5)
    @DateTimeFormatter("ddMMyyyy")
    private LocalDate dateInactivation;

}
```

```java
public class Main {
    
    public static void main(String[] args) {
        String regexColumnSeparator = "\\|";
        Path path = Paths.get("path", "to", "file");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader(regexColumnSeparator);
        
        try (JFileReader jFileReader = JFileReaderFactory.newInstance(path, readerConfiguration)) {
            jFileReader.forEach(User.class, user -> {
               System.out.println(user.getType()); 
               System.out.println(user.getActive()); 
               System.out.println(user.getName()); 
               System.out.println(user.getBigDecimal()); 
               System.out.println(user.getDateInactivation()); 
            });
        }
    }
}
```

## Validations

You can applly validations to the file using the class RuleConfigurator.

**Example**

```java
public class Main {
    
    public static void main(String[] args) {
        String regexColumnSeparator = "\\|";
        Path path = Paths.get("path", "to", "file");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader(regexColumnSeparator);

        RuleConfigurator
            .defaultConfigurator(readerConfiguration)
            .column(1)
            .localDateType("dd/MM/yyyy")
            .pastOrPresent()
            .column(2)
            .stringType()
            .regex(Pattern.compile("\\d+"))
            .build();
        
        try (JFileReader jFileReader = JFileReaderFactory.newInstance(path, readerConfiguration)) {
            List<RuleViolation> violations = jFileReader.validate();
            
            if (!violations.isEmpty()) {
                // handle violations
            }

            // it's ok? process lines
            jFileReader.forEach(User.class, user -> System.out.println(user));
        }
    }
}
```

# License

JFile-Reader is available under the [MIT license](https://tldrlegal.com/license/mit-license).
