# duct.module.cambium

[![Clojars Project](https://img.shields.io/clojars/v/duct.module.cambium.svg)](https://clojars.org/duct.module.cambium)
[![Circle CI](https://circleci.com/gh/lagenorhynque/duct.module.cambium.svg?style=shield)](https://circleci.com/gh/lagenorhynque/duct.module.cambium)
[![codecov](https://codecov.io/gh/lagenorhynque/duct.module.cambium/branch/master/graph/badge.svg)](https://codecov.io/gh/lagenorhynque/duct.module.cambium)

A Duct module that adds logging to a configuration, using [Cambium](https://cambium-clojure.github.io/). This is an alternative to [duct/module.logging](https://github.com/duct-framework/module.logging).

## Installation

To install, add the following to your project `:dependencies`:

```clj
[duct.module.cambium "0.1.0"]
```

## Usage

To add this module to your configuration, add a reference to `:duct.module/cambium`:

```edn
{:duct.profile/base
 {:duct.core/project-ns some-api

 :duct.profile/dev   #duct/include "dev"
 :duct.profile/local #duct/include "local"
 :duct.profile/prod  {}

 :duct.module/cambium {}}
```

And add `logback.xml` (or `logback-test.xml`) like below to the resource path.

```xml
<configuration>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
      <layout class="cambium.logback.json.FlatJsonLayout">
        <jsonFormatter class="ch.qos.logback.contrib.jackson.JacksonJsonFormatter">
          <!-- prettyPrint is probably ok in dev, but usually not ideal in production: -->
          <prettyPrint>true</prettyPrint>
        </jsonFormatter>
        <!-- <context>api</context> -->
        <timestampFormat>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</timestampFormat>
        <timestampFormatTimezoneId>UTC</timestampFormatTimezoneId>
        <appendLineSeparator>true</appendLineSeparator>
      </layout>
    </encoder>
  </appender>
  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

## Examples

- [lagenorhynque/clj-rest-api](https://github.com/lagenorhynque/clj-rest-api): an example REST API based on Pedestal & Duct
- [lagenorhynque/aqoursql](https://github.com/lagenorhynque/aqoursql): an example GraphQL API based on Lacinia-Pedestal & Duct
