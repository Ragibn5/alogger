# Changelog

All notable changes to PiTrackerCommons will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

### Added

- Basic logger interfaces and implementations.
  A few handy built-in logger implementations:
    - [ConsoleLogger](alogger/src/main/java/com/ragibn5/alogger/sources/ConsoleLogger.kt)
      A customizable logger that uses standard android.util.Log apis.
    - [FileLogger](alogger/src/main/java/com/ragibn5/alogger/sources/FileLogger.kt)
      A customizable file logger handling file creation, writing, log sequentiality and everything else for you.
    - [CompositeLogger](alogger/src/main/java/com/ragibn5/alogger/sources/CompositeLogger.kt)
      A logger implementation that takes other loggers as params and delegates logging to all of them.
      The default implementation (without you explicitly adding loggers) only uses a (default implementation of) `ConsoleLogger`.
      You may also create your own logger implementations (for example a network logger) using [Logger](alogger/src/main/java/com/ragibn5/alogger/sources/Logger.kt)
      interface, or extending the built-in logger implementations to add any extra feature on top.
