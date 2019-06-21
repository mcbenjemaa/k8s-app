# k8s-app
Simple app built with Spring Boot running in kubernetes.

This app uses [spring-cloud-kubernetes](https://github.com/spring-cloud/spring-cloud-kubernetes) and ``fabric8-maven-plugin``

to automate build process 

To set ``spring.profiles.active`` at runtime in kubernetes, you need to pass `SPRING_PROFILES_ACTIVE` Environment varible in the container.

```
 env:
  - name: SPRING_PROFILES_ACTIVE
    value: k8s

```
