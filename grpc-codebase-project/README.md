### NGINX

```
version: "3"
services:
  nginx:
    image: nginx:1.15-alpine
    volumes:
      - ./conf:/etc/nginx/conf.d
    ports:
      - 8585:8585
```
* Load Balancing: /conf folder
```
upstream bankservers {
    server 192.168.15.4:6565;
    server 192.168.15.4:7575;
}

server {

    listen 8585 http2;

    location / {
        grpc_pass grpc://bankservers;
    }

}
```