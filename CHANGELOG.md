# Changelog

## [Unreleased]
### Added
- Support for JSON logs

### Removed
- Custom environment variables with `LOGGING_LEVEL` prefix and updated documentation to use environment variables with `LOGGER_LEVELS` prefix supported out of the box by micronaut([#78](https://github.com/devatherock/velocity-template-tester/issues/78))

## [0.8.1] - 2021-03-28
### Changed
- Upgraded Kotlin to `1.4.32`
- The versioning parameter to `build_time` from `{build_time}`. Done to not impact running the application in local
- Prevented script error when an empty line is present in parameters text box

## [0.8.0] - 2021-03-13
### Added
- HTML, JS and CSS minification([#5](https://github.com/devatherock/velocity-template-tester/issues/5))
- Appended build time to static JS and CSS urls

### Changed
- Fixed release pipeline

## [0.7.0] - 2021-03-11
### Changed
- Accepted requests with no `parameters`([#19](https://github.com/devatherock/velocity-template-tester/issues/19))

### Removed
- Conversion to native image([#17](https://github.com/devatherock/velocity-template-tester/issues/17))

## [0.6.1] - 2020-06-14
### Changed
- Enabled debug logging for requests
- Enabled `/metrics` endpoint

## [0.6.0] - 2020-06-12
### Added
- `java.lang.String` to the reflection config as `substring` function isn't working within the template
- Built two docker images - one with jar and one with native image

## [0.5.0] - 2020-06-09
### Changed
- [Issue 6](https://github.com/devatherock/velocity-template-tester/issues/6): Built a native image using graal vm

## [0.4.2] - 2020-06-06
### Changed
- Corrected version number in swagger docs

## [0.4.1] - 2020-06-06
### Added
- Config to deploy the app to heroku
- Ability to use port from environment variable `PORT`. Required for heroku

### Changed
- Gradle version from `5.5` to `5.6.4`

## [0.4.0] - 2020-03-05
### Changed
- Fixed the mapping for static paths that broke the UI

## [0.3.0] - 2020-02-27
### Added
- Swagger integration to generate API specification

## [0.2.0] - 2020-02-27
### Added
- `/health` endpoint
- `latest` tag to the docker image
- build and docker pull badges to readme

## [0.1.0] - 2020-02-26
### Added
- Initial version that uses micronaut. Does not use a native image and static files(css, html, js) are not minified