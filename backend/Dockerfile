FROM tomcat:9.0-jdk11
WORKDIR /hitech
# 기존 ROOT 제거
RUN rm -rf /usr/local/tomcat/webapps/*

# 빌드된 WAR 복사
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8083
CMD ["catalina.sh", "run"]