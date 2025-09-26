FROM nginx:alpine
WORKDIR /hitech
# Nginx 설정 파일 복사
COPY ./conf.d /etc/nginx/conf.d

RUN mkdir -p /var/log/nginx && chmod -R 755 /var/log/nginx

EXPOSE 83
CMD ["nginx", "-g", "daemon off;"]