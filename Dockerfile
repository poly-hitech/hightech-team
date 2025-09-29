FROM tomcat:9.0-jdk11
WORKDIR /hightech
# 기존 ROOT 제거
RUN rm -rf /usr/local/tomcat/webapps/*

# 빌드된 WAR 복사
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# wait-for-it.sh 다운로드
RUN curl -sL https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
  -o /usr/local/tomcat/bin/wait-for-it.sh \
  && chmod +x /usr/local/tomcat/bin/wait-for-it.sh

EXPOSE 8083
CMD ["/bin/bash", "-c", "/usr/local/tomcat/bin/wait-for-it.sh oracle-db:1521 --timeout=300 --strict -- catalina.sh run"]