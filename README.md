[![CircleCI](https://circleci.com/gh/devatherock/velocity-template-tester.svg?style=svg)](https://circleci.com/gh/devatherock/velocity-template-tester)
[![Docker Pulls](https://img.shields.io/docker/pulls/devatherock/velocity-template-tester.svg)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
[![Docker Image Size](https://img.shields.io/docker/image-size/devatherock/velocity-template-tester.svg?sort=date)](https://hub.docker.com/r/devatherock/velocity-template-tester/)
[![Docker Image Layers](https://img.shields.io/microbadger/layers/devatherock/velocity-template-tester.svg)](https://microbadger.com/images/devatherock/velocity-template-tester)
# velocity-template-tester
UI/API for testing Apache Velocity templates

## API Reference
Refer the swagger [spec](https://velocity-template-tester.herokuapp.com/swagger/velocity-template-tester-0.4.2.yml)
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
