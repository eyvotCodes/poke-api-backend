# PokéAPI Backend

### Docs

La documentación de la práctica se encuentra en el directorio `/docs` de este mismo repo.

### Requirements

- Java 21
- Spring Boot 4.3.4
- Maven

### Setup

~~~
# instalación de JDKMAN! (para instalar Java, Spring Boot y Maven)
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh" # en caso de seguir usarndo la misma sesión del shell
$ sdk version # comprobar instalación

# instalación de maven
$ sdk install maven # última versión estable

# instalación de java
$ sdk install java 21-amzn # última lts optimizada para aws (corretto)

# instalación de spring boot
$ sdk install springbot # versión estable más reciente (3.4.3)
$ echo 'export JAVA_HOME="$HOME/.sdkman/candidates/java/current"' >> ~/.zshrc # Spring CLI ocupa dicha variable
$ source ~/.zshrc # en caso de seguir usarndo la misma sesión del shell (equipo con ZSH)
$ echo $JAVA_HOME # comprobar variable

# comprobaciones adicionales
$ java -version
$ mvn -version
$ spring --version
~~~
