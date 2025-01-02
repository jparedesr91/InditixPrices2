FROM eclipse-temurin:21-jdk-alpine as JRE_BUILDER
RUN apk --no-cache add openjdk21-jdk openjdk21-jmods
ENV MINIMAL_JAVA="/opt/minimal-java"
RUN "$JAVA_HOME"/bin/jlink \
    --verbose \
    --add-modules java.base,jdk.unsupported,jdk.management,jdk.crypto.ec,java.net.http,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,java.rmi \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --output "$MINIMAL_JAVA"

FROM alpine:3
ENV JAVA_HOME=/opt/minimal-java
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV JAVA_TOOL_OPTIONS="-Xss512k -XX:+UseSerialGC"
RUN apk add --no-cache libstdc++ \
    && addgroup -S app \
    && adduser -S -G app app
COPY --from=JRE_BUILDER "$JAVA_HOME" "$JAVA_HOME"
ARG JAR_FILE
COPY --chown=app:app ${JAR_FILE} /app/app.jar
WORKDIR /app
USER app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]