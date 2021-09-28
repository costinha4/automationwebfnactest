# Selenium Project in Java With Cucumber
This project includes six scenarios for testing Fnac website with selenium and cucumber. 

# Getting started 
This project is running in Java 11, Intellij (Maven to be installed) and using cucumber plugin (cucumber for java).

# Running in IntelliJ
To run in IntelliJ IDEA, Cucumber support is built-in, and you can even select a single Scenario within a Feature to run at a time or you can run all scenarios at once. For that you need to run the ```Runner.java``` file (located in folder \src\test\java\Runner).

# Project description
The project is organized by folder: 
-   in the 'drivers' folder is where the browser launchers are located;

    ```drivers/chromedriver.exe```  - to launch Chrome browser
    ```drivers/geckdriver.exe```    - to launch Firefox browser

-   in the 'Feature' folder where the files that stores data about features, their descriptions, and the scenarios to be tested are located;

    ```src/test/java/Features```

-   in the 'StepDefinitions' folder is located a file stores the mapping data between each step of a scenario defined in the feature file and the code to be executed;

    ```src/test/java/StepDefinitions```

-   in 'Helpers' folder is located a helper methods to make working with selenium;

    ```src/test/java/Runner```

-   in 'Runner' folder is the which is a JUnit Test Runner Class containing the Step Definition location and the other primary metadata required to run the test;
    
    ```src/test/java/Runner```

-   in 'Report' folder is located the file with the report of the last execution of the scenario/s
    
    ```src/test/java/Report```


# Maven Dependency
Project should be add as a dependency on pom.xml so it will be installed automatically
```
<dependencies>
    <dependency>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
    </dependency>
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-junit</artifactId>
      <version>6.11.0</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-java</artifactId>
      <version>6.11.0</version>
    </dependency>
    <dependency>
      <groupId>org.junit.vintage</groupId>
      <artifactId>junit-vintage-engine</artifactId>
      <version>5.7.0</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-chrome-driver</artifactId>
      <version>3.141.59</version>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>3.141.59</version>
    </dependency>
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>6.8.13</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-api</artifactId>
      <version>3.141.59</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>2.13.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.13.0</version>
    </dependency>
</dependencies>
```

