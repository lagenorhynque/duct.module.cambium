# duct.module.cambium

[![Clojars Project](https://img.shields.io/clojars/v/duct.module.cambium.svg)](https://clojars.org/duct.module.cambium)
[![Circle CI](https://circleci.com/gh/lagenorhynque/duct.module.cambium.svg?style=shield)](https://circleci.com/gh/lagenorhynque/duct.module.cambium)
[![codecov](https://codecov.io/gh/lagenorhynque/duct.module.cambium/branch/master/graph/badge.svg)](https://codecov.io/gh/lagenorhynque/duct.module.cambium)

A Duct module that adds logging to a configuration, using [Cambium](https://cambium-clojure.github.io/). This is an alternative to [duct/module.logging](https://github.com/duct-framework/module.logging).

## Installation

To install, add the following to your project `:dependencies`:

```clj
[duct.module.cambium "1.3.1"]
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

- `:duct.module/cambium` can have one option:
    - `:top-level-field`: top-level field name for nested data of MDC map (default: `:option`)

And add `logback.xml` (or `logback-test.xml`) as below to the resource path.

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

- [lagenorhynque/clj-rest-api](https://github.com/lagenorhynque/clj-rest-api): an example REST API based on [Pedestal](https://github.com/pedestal/pedestal) & Duct
- [lagenorhynque/aqoursql](https://github.com/lagenorhynque/aqoursql): an example GraphQL API based on [Lacinia-Pedestal](https://github.com/walmartlabs/lacinia-pedestal) & Duct
- [lagenorhynque/route-guide](https://github.com/lagenorhynque/route-guide): an example gRPC API based on [Protojure](https://github.com/protojure/lib), Pedestal & Duct

## License

Copyright © 2019 Kent OHASHI

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
