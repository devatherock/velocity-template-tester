[![CircleCI](https://circleci.com/gh/devatherock/velocity-template-tester.svg?style=svg)](https://circleci.com/gh/devatherock/velocity-template-tester)
[![Version](https://img.shields.io/docker/v/devatherock/velocity-template-tester?sort=semver)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
[![Coverage Status](https://coveralls.io/repos/github/devatherock/velocity-template-tester/badge.svg?branch=master)](https://coveralls.io/github/devatherock/velocity-template-tester?branch=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=velocity-template-tester&metric=alert_status)](https://sonarcloud.io/component_measures?id=velocity-template-tester&metric=alert_status&view=list)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=velocity-template-tester&metric=ncloc)](https://sonarcloud.io/component_measures?id=velocity-template-tester&metric=ncloc)
[![Docker Image Size](https://img.shields.io/docker/image-size/devatherock/velocity-template-tester.svg?sort=date)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
# velocity-template-tester
UI/API for testing [Apache Velocity](https://velocity.apache.org/engine/2.2/user-guide.html) templates

## API Reference
Refer the swagger [spec](https://velocity-template-tester.herokuapp.com/swagger/velocity-template-tester-0.6.1.yml)
or the swagger [UI](https://velocity-template-tester.herokuapp.com/swagger-ui/)

### Key parameters:
- **Endpoint**: `https://velocity-template-tester.herokuapp.com/api/expandTemplate`
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
  devatherock/velocity-template-tester:latest
```