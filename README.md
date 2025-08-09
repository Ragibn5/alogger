# alogger

A simple and extensible android logging solution with handy pre-built loggers.

## Prerequisites

- **Android Studio**: Ladybug (2024.2.2) or later
- **AGP**: 8.8.2 or later
- **Java runtime (Along with Gradle JDK)**: JDK 17 or later

## Installation & Setup

### 1. Clone the Repository

Using HTTPS:

```bash
git clone https://github.com/Ragibn5/alogger.git
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **File** → **Open** and navigate to the cloned directory

### 3. Sync Project

Android Studio will automatically prompt to sync the project.
If not, click **File** → **Sync Project with Gradle Files**.
Make sure you have met the prerequisites described above, otherwise the sync may not be successful.

### Usage

```kotlin
// Using pre-built console logger implementation.
val logger = ConsoleLogger()

// With `log(LogData)` method.
logger.log(
    LogData(
        tag = "EXAMPLE",
        type = LogLevel.DEBUG,
        message = "Example log",
    )
)

// With `debug`, `info`, `warning` and `error` extension methods.
logger.debug(tag = "EXAMPLE", message = "Example log")
logger.info(tag = "EXAMPLE", message = "Example log")
logger.warning(tag = "EXAMPLE", message = "Example log")
logger.error(
    tag = "EXAMPLE",
    message = "Example log",
    throwable = Exception("Example exception")
)
```

Available pre-built logger implementations:

- [ConsoleLogger](alogger/src/main/java/com/ragibn5/alogger/sources/ConsoleLogger.kt)
  A customizable logger that uses standard android.util.Log apis.
- [FileLogger](alogger/src/main/java/com/ragibn5/alogger/sources/FileLogger.kt)
  A customizable file logger handling file creation, writing, log sequentiality and everything else for you.
- [CompositeLogger](alogger/src/main/java/com/ragibn5/alogger/sources/CompositeLogger.kt)
  A logger implementation that takes other loggers as params and delegates logging to all of them.
  The default implementation (without you explicitly adding loggers) only uses a (default implementation of) `ConsoleLogger`.
  You may pass pre-built ones, or any custom implementations to this composite logger.

  **_Note:_** **DO NOT** pass self reference to the composite logger.

You may also create your own logger implementations (for example a network logger) using
the [Logger](alogger/src/main/java/com/ragibn5/alogger/sources/Logger.kt) interface,or
extending the built-in logger implementations to add any extra feature on top.

## Building a New Local Release

Currently, we use local maven publishing method to create a release and use it as a library (in
other projects).

To build and publish a new release version of the library (to your local Maven repository), run:

```bash
./gradlew clean assembleRelease publishToMavenLocal
```

**Note:**
Make sure you change the version inside the `publishing` block of the library's
[`build.gradle`](alogger/build.gradle.kts) file before creating a new local release.
Running the above command while not changing the version will overwrite the previous release build
files.

## Using in Other Projects

Add this to your build.gradle:

```groovy
implementation 'com.ragibn5:alogger:0.0.1'
```

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a Pull Request

## License

Click [here](LICENSE) to see the license.