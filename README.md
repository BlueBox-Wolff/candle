![Gradle CI](https://github.com/BlueBox-Wolff/candle/workflows/Gradle%20CI/badge.svg)

# candle
Candle is a library which contains usefull classes for many topics.

# How to use
Maven:
```xml
<repository>
    <name>spleef-repo</name>
    <url>https://repo.spleef.eu/repository/spleef-public/</url>
</repository>
```

```xml
<dependency>
    <groupId>de.bluebox.wolff</groupId>
    <artifactId>candle</artifactId>
    <version>1.0.0</version>
</dependency>
```

Gradle:
```gradle
repositories {
    maven {
        url "https://repo.spleef.eu/repository/spleef-public/"
    }
}
```

```gradle
dependencies {
    compile group: "de.bluebox.wolff", name: "candle", version: "1.0.0";
}
```
