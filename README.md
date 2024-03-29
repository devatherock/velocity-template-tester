[![CircleCI](https://circleci.com/gh/devatherock/velocity-template-tester.svg?style=svg)](https://circleci.com/gh/devatherock/velocity-template-tester)
[![Version](https://img.shields.io/docker/v/devatherock/velocity-template-tester?sort=semver)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=velocity-template-tester&metric=coverage)](https://sonarcloud.io/component_measures?id=velocity-template-tester&metric=coverage)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=velocity-template-tester&metric=alert_status)](https://sonarcloud.io/component_measures?id=velocity-template-tester&metric=alert_status&view=list)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=velocity-template-tester&metric=ncloc)](https://sonarcloud.io/component_measures?id=velocity-template-tester&metric=ncloc)
[![Docker Image Size](https://img.shields.io/docker/image-size/devatherock/velocity-template-tester.svg?sort=date)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
# velocity-template-tester
UI/API for testing [Apache Velocity](https://velocity.apache.org/engine/2.2/user-guide.html) templates

## API Reference
Refer the swagger [spec](https://velocity-template-tester.onrender.com/swagger/velocity-template-tester-1.2.0.yml)
or the swagger [UI](https://velocity-template-tester.onrender.com/swagger-ui/)

### Key parameters:
- **Endpoint**: `https://velocity-template-tester.onrender.com/api/expandTemplate`
- **Request Content-Type**: `application/json`, `application/x-yaml`
- **Response Content-Type**: `text/plain`

### Sample JSON Payload:
```json
{
  "template": "Hello ${user}",
  "parameters": {
    "name": "John Doe"
  }
}
```

### Sample YAML Payload:
```yaml
template: Hello ${user}
parameters:
  name: John Doe
```

## Usage
### Sample command

```
docker run --rm \
  -p 8080:8080 \
  devatherock/velocity-template-tester:1.0.0
```

## Troubleshooting
### Enabling debug logs
- Set the environment variable `LOGGER_LEVELS_ROOT` to `DEBUG` to enable all debug logs - custom and framework
- Set the environment variable `LOGGER_LEVELS_IO_GITHUB_DEVATHEROCK` to `DEBUG` to enable debug logs only in custom code
- For fine-grained logging control, supply a custom [logback.xml](http://logback.qos.ch/manual/configuration.html) file
and set the environment variable `JAVA_OPTS` to `-Dlogback.configurationFile=/path/to/custom/logback.xml`

### JSON logs

To output logs as JSON, set the environment variable `JAVA_OPTS` to `-Dlogback.configurationFile=logback-json.xml`. Refer
[logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder) documentation to customize the field names and 
formats in the log
