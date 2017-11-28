[![Build Status](https://travis-ci.org/skjolber/dsf.svg?branch=master)](https://travis-ci.org/skjolber/dsf)

# dsf
Simple utility for interaction with the Norwegian national service `Det norske folkeregisteret`.


## License
[Apache 2.0]

# Obtain
The project is based on [Maven] and is available at central Maven repository.

Example dependency config

```xml
<dependency>
    <groupId>com.github.skjolber</groupId>
    <artifactId>dsf</artifactId>
    <version>1.0.0</version>
</dependency>
```

# Usage
The simple utility contains a simple mapper `NorwegianResidentRegisterCountryMapper`.

```java
NorwegianResidentRegisterCountryMapper mapper = new NorwegianResidentRegisterCountryMapper();


String countryCode = mapper.toISO3166("000") // NO

// or


String dsfCountryCode= mapper.fromISO3166("NO"); /// 000

```

# Implementation details
We are mapping folkeregisteret to ISO-3166-1 codes via country name.

The ISO-3166 mapping was created by combining the resources

  * http://www.foseid.priv.no/gustav/iso3166/
  * https://www.skatteetaten.no/globalassets/skjemaer/landkoder-fra-det-sentrale-folkeregister-dsf.pdf

where the ISO3166 source has been modified to include all the countries in folkeregisteret. A few countries have been excluded, see hastaged lines in `folkeregisteret_countries.txt`.

# History

 - [1.0.0]: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/dsf/issues
[Maven]:                http://maven.apache.org/
[1.0.0]:				https://github.com/skjolber/dsf/releases/tag/dsf-1.0.0