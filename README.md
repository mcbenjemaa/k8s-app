# k8s-app
Simple app built with Spring Boot running in kubernetes.

This app uses [spring-cloud-kubernetes](https://github.com/spring-cloud/spring-cloud-kubernetes) and ``fabric8-maven-plugin`` to automate build process 

The app had 2 profiles `dev` and `kubernetes`, meaning local develoment or running in kubernetes.

before we go we need to set required configMap, first the USERNAME:

```
kubectl create cm user-config --from-literal=name=Medchiheb
```

then the ``app-config``

```
kubectl create cm k8s-config --from-file=application.properties
```

By `spring-cloud-kubernetes-config` the config will loaded at bootstrap using our configMap created. to do so include configMap name in `bootstrap.properties` or `yml`

```
spring.cloud.kubernetes.config.name=k8s-config
```

To set ``spring.profiles.active`` at runtime in kubernetes, you need to pass `SPRING_PROFILES_ACTIVE` Environment variable in the container.

```
 env:
  - name: SPRING_PROFILES_ACTIVE
    value: kubernetes

```

## building ##

first build the project using Maven, this build will also create the resources yaml files  in `${basedir}/target/classes/METAINF/fabric8/kubernetes`. and will build the image.

``` mvn clean install ```

then you need a runing cluster on your machine, just run follwing command to deploy.

``` mvn fabric8:apply ```
