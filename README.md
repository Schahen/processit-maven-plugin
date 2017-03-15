Maven Processit Plugin
==

`Processit` is a maven plugin intentionally designed to be as simple 
as possible. Basically it does one thing - it provides interface for
implementing you own processor logic, whatever this logic will be. 


Usage
--
Here's example of it's usage:

```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.shabunc.maven</groupId>
        <artifactId>processit</artifactId>
        <!-- This is indeed the latest version -->
        <version>1.0.2</version>
        <dependencies>
          <dependency>
            <groupId>my.very.own.processors.collection</groupId>
            <artifactId>processor</artifactId>
            <version>1.0-SNAPSHOT</version>
          </dependency>
        </dependencies>
        <executions>
          <execution>
            <id>process</id>
            <goals>
              <goal>run</goal>
            </goals>
            <phase>generate-sources</phase>
          </execution>
        </executions>
        <configuration>
          <processors>
            <processor>my.very.own.processors.collection.Processor</processor>
          </processors>
        </configuration>
      </plugin>
    <plugins>
</build>
```


Basically if your processor must implement a very simple 
interface, `org.shabunc.maven.Processor` which have following method
declarations:

```java
void setProject(MavenProject mavenProject);
Iterator<File> getFiles();
void process(File file);
```
so, in getFiles you collect files you want to, in process - well, process
(you do not have to call process in getFiles - plugin will iterate through
getFiles).

That's basically  it.
 
Now you can just run `mvn generate-sources` (or whatever phase you will declare)
Also, you can just run `mvn processit:run` 

Oh, one more thing.

Installation
 --

Maven Processit Plugin lives in a separate repository one can invoke by
using following definition in your `pom.xml` file:

```xml
<repositories>
    <repository>
      <id>schahen-mvn-repo</id>
      <url>https://raw.github.com/schahen/processit-maven-plugin/mvn-repo/</url>
    </repository>
</repositories>
```

