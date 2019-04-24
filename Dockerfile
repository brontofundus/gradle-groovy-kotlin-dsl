FROM azul/zulu-openjdk:8

VOLUME ["/tmp"]

EXPOSE 8080

RUN adduser --no-create-home --disabled-login --disabled-password javauser
USER javauser

ARG JARFILE
ARG VERSION

ADD $JARFILE /

ENV EXAMPLE_SERVICE_JAR=$JARFILE
ENV EXAMPLE_SERVICE_JAR_VERSION=$VERSION

ENTRYPOINT ["sh", "-c", "exec java -Xms200m -Xmx512m -Duser.timezone=Europe/Zurich -jar /$EXAMPLE_SERVICE_JAR"]
