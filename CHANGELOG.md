# Changelog

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