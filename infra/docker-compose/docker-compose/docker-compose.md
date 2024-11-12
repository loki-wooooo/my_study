[TOC]

# Docker Compose

도커 컴포즈의 필요성을 이해하고 다양한 사용법 실습합니다.

# 사전 준비
## docker 설치
```shell
curl -fsSL https://get.docker.com/ | sudo sh

sudo usermod -aG docker $USER
```

# LAB 3. Docker Compose
목적: 여기서는 도커 컴포즈의 필요성을 이해하고 도커 컴포즈로 기본적인 개발 환경을 구축해봅니다.

## docker-compose 설치하기

```shell
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
```

### 설치 확인
```shell
docker-compose version
```

## (선택사항) 명령어 축약하기
docker-compose가 너무 길어서 귀찮을 때.

```shell
# ~/.bashrc 파일 가장 아랫 부분에 다음 내용을 추가
alias dco='docker-compose'
```

```shell
source ~/.bashrc
dco
```

## docker-compose를 사용하는 이유

### 1. docker 실행 명령어를 일일이 입력하기가 복잡해서
예시 1) nginx 컨테이너 실행

```shell
docker run -it nginx
```



예시 2) nginx 컨테이너 실행 + 호스트의 8080 포트 연결

```shell
docker run -it -p 8080:80 nginx
```



예시 3) nginx 컨테이너 실행 + 호스트의 8080 포트 연결 + 컨테이너 종료시 자동 삭제

```shell
docker run -it -p 8080:80 --rm nginx
```

예시 4) nginx 컨테이너 실행 + 호스트의 8080 포트 연결 + 컨테이너 종료시 자동 삭제 + 호스트의 디렉터리를 컨테이너 안에 링크

```html
# ~/project/nginx/index.html

<html>
<body>
<h1>Hello Docker-Compose</h1>
</body>
</html>
```

```shell
docker run -it -p 8080:80 --rm -v $(pwd):/usr/share/nginx/html/ nginx
```



### 2. 컨테이너끼리 연결하기 편해서
준비) django-sample 이미지를 빌드합니다

```shell
git clone https://github.com/raccoonyy/django-sample-for-docker-compose.git django-sample

cd django-sample

docker build -t django-sample .
```

예시 1) django 컨테이너 실행 + postgres 컨테이너 실행

```shell
docker run --rm -d --name django \
  -p 8000:8000 \
  django-sample

docker run --rm -d --name postgres \
  -e POSTGRES_DB=djangosample \
  -e POSTGRES_USER=sampleuser \
  -e POSTGRES_PASSWORD=samplesecret \
  postgres
```



예시 2) postgres 컨테이너 실행 + django 컨테이너 실행 + 서로 연결하기

```shell
docker run --rm -d --name postgres \
  -e POSTGRES_DB=djangosample \
  -e POSTGRES_USER=sampleuser \
  -e POSTGRES_PASSWORD=samplesecret \
  postgres

docker run -d --rm \
  -p 8000:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample
```



### 3. 특정 컨테이너끼리만 통신할 수 있는 가상 네트워크 환경을 편리하게 관리하고 싶어서
예시 1) postgres 컨테이너 실행 + django1 컨테이너 연결

```shell
docker run --rm -d --name postgres \
  -e POSTGRES_DB=djangosample \
  -e POSTGRES_USER=sampleuser \
  -e POSTGRES_PASSWORD=samplesecret \
  postgres

docker run -d --rm --name django1 \
  -p 8000:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample
```



예시 2) postgres 컨테이너는 호스트의 다른 컨테이너들이 모두 접근할 수 있음

```shell
docker run -d --rm --name django2 \
  -p 8001:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample
```



예시 3) postgres 컨테이너 + django1 컨테이너만 통신할 수 있는 가상 네트워크 만들기

```shell
# 도커 네트워크 살펴보기
docker network ls
```

```shell
# 도커 네트워크 생성하기
docker network create --driver bridge web-service

docker network ls
```



```shell
# 컨테이너 실행하기
docker run --rm -d --name postgres \
  --network web-service \
  -e POSTGRES_DB=djangosample \
  -e POSTGRES_USER=sampleuser \
  -e POSTGRES_PASSWORD=samplesecret \
  postgres

docker run -d --rm --name django1 \
  --network web-service \
  -p 8000:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample

docker run -d --rm --name django2 \
  -p 8001:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample
```



* docker의 네트워크 모드 종류
* bridge: 해당 네트워크 안에서만 통신 가능
* host: 호스트와 똑같은 네트워크 환경
* none: 아무 네트워크도 사용하지 않음

### 4. 이 모든 것을 간단한 명령어로 관리하고 싶어서

```shell
# 실행 명령어와 종료 명령어
docker network create --driver bridge web-service

docker run --rm -d --name postgres \
  --network web-service \
  -p 5432:5432 \
  -e POSTGRES_DB=djangosample \
  -e POSTGRES_USER=sampleuser \
  -e POSTGRES_PASSWORD=samplesecret \
  postgres

docker run -d --rm --name django1 \
  --network web-service \
  -p 8000:8000 \
  -e DJANGO_DB_HOST=db \
  --link postgres:db \
  django-sample

docker kill django1 postgres

docker network rm web-service
```

docker-compose.yml

```yaml
version: '3'

volumes:
  postgres_data: {}

services:
  db:
    image: postgres
    volumes:
      - postgres_data:/var/lib/postgres/data
    environment:
      - POSTGRES_DB=djangosample
      - POSTGRES_USER=sampleuser
      - POSTGRES_PASSWORD=samplesecret

  django:
    build:
      context: .
      dockerfile: ./compose/django/Dockerfile-dev
    volumes:
      - ./:/app/
    command: ["./manage.py", "runserver", "0:8000"]
    environment:
     - DJANGO_DB_HOST=db
    depends_on:
      - db
    restart: always
    ports:
      - 8000:8000
```

도커 컴포즈로 실행하고 종료하기
```shell
docker-compose -d up

docker-compose down
```



## 실습: NGINX 서버를 도커 컴포즈로 실행하기

### docker run 명령어로 nginx 서버실행
| 정보  |  내용  |
|---|---|
| 이미지 | nginx:latest |
| Listening Port | 80 |
| HTML 경로 | /usr/share/nginx/html |

* 호스트의 60080 포트를 컨테이너의 80 포트로 연결하세요.
* index.html 파일을 만들고, NGINX 접속시 이 파일이 나타나게 해보세요.

```shell
docker run -it -p 60080:80 -v $(pwd):/usr/share/nginx/html/ nginx
```

### docker-compose.yml로 nginx 서버 실행하기

```yaml
version: '3'

services:
  nginx:
    image: nginx
    ports:
      - 60080:80
    volumes:
      - ./:/usr/share/nginx/html/
```

## docker-compose.yml 파일 문법

### version

docker-compose.yml 파일의 명세 버전

```yaml
version: '3'
```

### services

실행할 컨테이너를 정의 (`docker run --name django`와 같다고도 생각할 수 있음)

```yaml
services:
  postgres:
    ...
  django:
    ...
```

### image

컨테이너에 사용할 이미지 이름과 태그 (태그를 생략하면 latest)

```yaml
services:
  django:
    image: django-sample
```

### build

컨테이너에 사용할 이미지를 직접 빌드할 때 `image` 대신 `build` (별도의 Dockerfile이 필요함)

```yaml
services:
  django:
    build:
      context: .
      dockerfile: ./compose/django/Dockerfile-dev
```

### ports

컨테이너와 연결할 포트(들)

{호스트포트}:{컨테이너포트}

```yaml
services:
  django:
    ...
    ports:
      - "8000:8000"
```

### environment

컨테이너에서 사용할 환경변수(들)

{환경변수이름}:{값}

```yaml
services:
  mysql:
    ...
    environment:
      - MYSQL_ROOT_PASSWORD=somewordpress
```

### volumes

마운트하려는 디렉터리(들)

{호스트 디렉터리}:{컨테이너 디렉터리}

```yaml
services:
  django:
    ...
    volumes:
      - ./app:/app
```

### link (legacy)

다른 컨테이너와 연결

{연결할 컨테이너 이름}:{해당 컨테이너에서 참조할 이름}

```yaml
services:
  mysql:
    ...
  django:
    ...
    link:
     - mysql:db
```

### depends_on

컨테이너 실행 의존성

```yaml
services:
  mysql:
    ...
  django:
    ...
    depends_on:
     - mysql
```

## 데모: Ghost 블로그를 도커 컴포즈로 실행하기
Ghost는 간단한 블로깅 시스템입니다. Ghost에서 제공하는 [공식 도커 이미지](https://hub.docker.com/_/ghost/)를 사용하면, 간단하게 Ghost 시스템을 실행할 수 있습니다.

### Ghost 블로그를 실행하는 docker run 명령어를 작성합니다.
`docker run` 명령으로 ghsot 이미지를 실행합니다.
```shell
docker run -it --name blog --rm -p 2368:2368 ghost
```

브라우저에서 http://IP:2368/ghost 를 열어봅시다.
관리자 설정을 해보고, 글을 새로 작성하거나 샘플 글을 수정해봅시다.

이제, 컨테이너를 종료한 후 앞선 명령을 한 번 더 실행해봅시다.
```shell
docker run --rm -p 2368:2368 --name blog ghost
```

브라우저를 열어 관리자 페이지(http://IP:2368/ghost)에 가보면, 이전에 설정한 내용이 모두 사라졌음을 확인할 수 있습니다.

현재까지 컨테이너의 모습은 다음과 같습니다.


블로그 데이터는 영속적이어야하므로, 데이터가 로컬 디렉터리에 저장되게 해봅시다. 데이터를 저장할 `content` 디렉터리를 만들고,
```shell
mkdir content
```

다음 명령으로 컨테이너를 실행합니다.
```shell
docker run --rm -p 2368:2368 --name blog -v $(pwd)/content:/var/lib/ghost/content ghost
```

이제 http://IP:62368/ghost 에서 다시 한 번 관리자 설정을 하고, 컨테이너를 종료한 후 다시 실행해봅시다.

볼륨을 연결한 이후의 컨테이너는 다음과 같은 모습입니다.



### 앞에서 완성한 도커 명령을 `ghost/docker-compose.yml` 파일로 옮겨봅시다.
먼저, 앞서 실행했던 Ghost 컨테이너를 종료합니다.
```shell
docker kill blog
```

이제 다음과 같이 `ghost/docker-compose.yml` 파일을 만듭니다.
```yaml
version: '3'

services:
  ghost:
    image: ghost
    ports:
      - "2368:2368"
```

* docker 명령어의 v 옵션은 volumes와 같습니다.
```yaml
...
volumes:
  - {folder_in_host}:{folder_in_container}
```

앞선 `docker-compose.yml` 파일에 빠진 볼륨 연결 설정을 추가해봅시다.

```yaml
version: '3'

services:
  ghost:
    image: ghost
    ports:
      - "2368:2368"
    volumes:
      - ./content:/var/lib/ghost/content
```

`docker-compose.yml` 파일에 적힌 내용을 바탕으로 Ghost 컨테이너를 실행해봅시다.
```shell
docker-compose up
```

[http://IP:2368/ghost]에 접속하여 잘 실행되었는지 확인해봅니다.

컨테이너를 종료하려면 다음 명령을 사용합니다.
```shell
docker-compose down
```

### 영속 데이터를 호스트가 아닌 docker 볼륨에 저장하기

```yaml
version: '3'

volumes: 
  ghost_data: {}

services:
  ghost:
    image: ghost
    ports:
      - "2368:2368"
    volumes:
      - ghost_data:/var/lib/ghost/content
```

이제 로컬의 디렉터리를 삭제해봅시다.
```shell
rm -rf content
```

content의 데이터가 사라졌으니 설정은 다시 해야겠지만, 앞으로는 디렉터리 대신 docker volume에 데이터가 저장됩니다.

```shell
docker-compose up

docker-compose down 

docker-compose up
```

### Ghost 시스템과 NGINX 연결하기
Ghost 시스템은 그 자체로도 잘 작동하지만 앞단에 NGINX 같은 웹 서버를 둠으로써 처리 성능을 높이거나 여타 기능을 사용할 수 있습니다. 이 구조를 `docker-compose.yml`에 구현해봅시다.

docker-compose를 이용하여 여러개의 컨테이너 연결

* 기존: (요청) -> ghost
* nginx 사용: (요청) -> nginx -> ghost

```yaml
# docker-compose.yml
version: '3'

volumes: 
  ghost_data: {}

services:
  ghost:
    image: ghost
    volumes:
      - ghost_data:/var/lib/ghost/content
  nginx:
    image: nginx
    volumes: 
      - ./nginx.conf:/etc/nginx/nginx.conf
    ports:
      - 8000:80
```

> ghost 서비스 쪽에서 ports가 사라진 것과 nginx 서비스의 포트 연결을 주의 깊게 살펴보세요.
그리고 다음과 같이 `nginx.conf` 파일을 생성합니다.
```nginx
# nginx.conf
events {
  worker_connections 1024;
}

http {
  upstream ghost {
    server ghost:2368;
  }
  
  server {
    listen 80;
    server_name _;

    location / {
      proxy_pass http://ghost;
    }
  }
}
```

이제 도커 컴포즈로 nginx, ghost 서비스를 다시 실행한 후 http://IP:8000 으로 접속할 수 있는지 확인해봅시다.

Nginx 컨테이너를 연결한 모습은 다음과 같습니다.


### 환경변수 추가하기

* docker 명령어의 e 옵션은 environment와 같습니다.
```yaml
...
environment:
  - {ENV_NAME}={ENV_VALUE}
```

http://IP:8000/ghost/#/site 에 가보면 미리보기가 제대로 나타나지 않는 것을 확인할 수 있습니다. Ghost 내부의 주소를 나타내는 환경변수의 기본 값이 localhost:8000이기 때문입니다.
ghost 컨테이너의 환경변수로 `http://IP:8000`를 추가해봅시다.

```yaml
# docker-compose.yml
version: '3'

volumes:
  - ghost_data: {}

services:
  ghost:
    image: ghost
    volumes:
      - ghost_data:/var/lib/ghost/content
    environment:
      - url=http://IP:8000
  nginx:
    image: nginx
    volumes: 
      - ./nginx.conf:/etc/nginx/nginx.conf
    ports:
      - 8000:80
```

* ghost 서비스의 환경변수 설정은 https://docs.ghost.org/concepts/config/#section-running-ghost-with-config-env-variables 문서를 참고하였습니다.

파일을 저장하고 컨테이너를 다시 실행합니다.
```shell
docker-compose down

docker-compose up -d
```

이제 도커 컴포즈로 서비스를 다시 실행한 후 http://IP:8000/ghost/#/site 에서 미리보기가 제대로 나타나는지 확인해봅시다.

## docker-compose 명령어

### docker-compose ps
현재 실행 중인 서비스 목록을 보여줍니다.

### docker-compose pull [service]
필요한 이미지를 다운받습니다.

### docker-compose build [service]
필요한 이미지를 빌드합니다.

### docker-compose create [service]
서비스를 생성합니다.

### docker-compose start [service]
서비스의 컨테이너를 구동합니다.

### * docker-compose up [service] = (build) + create + start
서비스를 구동합니다. (서비스와 네트워크가 없으면 만들고, 이미지가 없으면 빌드도 합니다.)

* -d: 데몬 모드로 실행합니다.
* --build: 강제로 이미지를 다시 빌드합니다.
* --force-recreate: 컨테이너를 새로 생성합니다.

### docker-compose logs [service]

* -f: 로그 계속 보기

### docker-compose stop [service]
서비스를 멈춥니다.

### docker-compose kill [service]
서비스의 컨테이너들을 삭제합니다.

### * docker-compose down [service] = stop + kill
서비스를 멈추고 컨테이너를 삭제합니다.

* -v: 도커 볼륨도 함께 삭제합니다.

### * docker-compose run {service} {command}
해당 서비스에 컨테이너를 하나 더 실행합니다.

* -e: 환경변수를 설정합니다.
* -p: 연결할 포트를 설정합니다.
* --rm: 컨테이너 종료시 자동으로 삭제합니다.

### * docker-compose exec {container} {command}
해당 서비스의 컨테이너에서 명령어를 실행합니다.

* -e: 환경변수를 설정합니다.

## 실습: 워드프레스를 도커 컴포즈로 실행하기
### Wordpress 컨테이너
|  설정  |  설명  |
|---|---|
| 이미지 | wordpress |
| Listening Port | 80 |
| WORDPRESS_DB_HOST 환경변수 | 디비 주소 (db:3306) |
| WORDPRESS_DB_USER 환경변수 | 디비 사용자 (wp) |
| WORDPRESS_DB_PASSWORD 환경변수 | 디비 패스워드 (wp) |
| WORDPRESS_DB_NAME 환경변수 | 디비 이름 (wp) |

### MySQL 컨테이너
|  설정  |  설명  |
|---|---|
| 이미지 | mysql:5.7 |
| Listening Port | 3306 |
| MYSQL_ROOT_PASSWORD 환경변수 | 디비 root 비밀번호 |
| MYSQL_DATABASE 환경변수 | 디비 데이터베이스 (wp) |
| MYSQL_USER 환경변수 | 디비 사용자 (wp) |
| MYSQL_PASSWORD 환경변수 | 디비 패스워드 (wp) |
| 디비 데이터 저장 디렉토리 | /var/lib/mysql |

```yaml
version: '3.3'

services:
   db:
     image: mysql:5.7
     volumes:
       - db_data:/var/lib/mysql
     environment:
       MYSQL_ROOT_PASSWORD: somewordpress
       MYSQL_DATABASE: wordpress
       MYSQL_USER: wordpress
       MYSQL_PASSWORD: wordpress

   wordpress:
     image: wordpress:latest
     ports:
       - "60000:80"
     restart: always
     environment:
       WORDPRESS_DB_HOST: db:3306
       WORDPRESS_DB_USER: wordpress
       WORDPRESS_DB_PASSWORD: wordpress
       WORDPRESS_DB_NAME: wordpress

volumes:
    db_data: {}
```

## 데모: 도커 컴포즈로 Django 개발 환경 구성하기
Postgres 컨테이너
```yaml
version: '3'

volumes:
  postgres_data: {}

services:
  db:
    image: postgres
    volumes:
      - postgres_data:/var/lib/postgres/data
    environment:
      - POSTGRES_DB=djangosample
      - POSTGRES_USER=sampleuser
      - POSTGRES_PASSWORD=samplesecret
```

Django 컨테이너
```yaml
  django:
    build:
      context: .
      dockerfile: ./compose/django/Dockerfile-dev
    volumes:
      - ./app:/app
    command: ["./manage.py", "runserver", "0:8000"]
    environment:
      - DJANGO_DB_HOST=db
    depends_on:
      - db
    ports:
      - 8000:8000
```

개발 환경 구성을 위한 별도의 Dockerfile
```dockerfile
# ./compose/django/Dockerfile-dev
FROM python:3

ENV PYTHONUNBUFFERED 1

WORKDIR /app
# COPY    ./app   /app/
COPY    ./app/requirements.txt    /app/
RUN     pip install -r requirements.txt

EXPOSE 8000

# CMD ["python", "manage.py", "runserver", "0:8000"]
```

## 실습: 환경변수 추가시 적용 순서

### Dockerfile-dev 파일에 다음 내용을 추가합니다
* ENV TEST_ENV dockerfile_value

### docker-compose.yml 파일의 django 컨테이너 아래 다음 내용을 추가합니다
```yaml
  django:
    ...
    environment:
      - TEST_ENV=compose_value
```

### 실제 환경변수의 값을 확인합니다
```shell
$ docker-compose run django bash
echo $TEST_ENV
```

## 개인 실습
프론트엔드와 백엔드, 데이터베이스까지 한꺼번에 도커 컴포즈로 실행해봅니다.

### 실습 1. Flask(python) + Redis 웹 서비스 실행하기
다음의 컨테이너 실행 조건과 파일 내용을 바탕으로 flask 앱을 빌드하고 도커 컴포즈로 컨테이너를 실행해봅니다.

**flask 컨테이너**

|  설정  |  설명  |
|---|---|
| 연결할 포트 | 5000 |
| redis 호스트 이름 | redis |

**redis 컨테이너**

|  설정  |  설명  |
|---|---|
| 이미지 | redis |

**app.py**

```python
import time

import redis
from flask import Flask

app = Flask(__name__)
cache = redis.Redis(host='redis', port=6379)


def get_hit_count():
    retries = 5
    while True:
        try:
            return cache.incr('hits')
        except redis.exceptions.ConnectionError as exc:
            if retries == 0:
                raise exc
            retries -= 1
            time.sleep(0.5)


@app.route('/')
def hello():
    count = get_hit_count()
    return 'Hello World! I have been seen {} times.\n'.format(count)
```

**requirements.txt**

```txt
flask
redis
```

**Dockerfile**

```dockerfile
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP app.py
ENV FLASK_RUN_HOST 0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
COPY . .
CMD ["flask", "run"]
```

** 확인 순서**

1. flask 애플리케이션을 빌드하여 이미지를 만듭니다.
2. 50000 포트로 접속할 수 있게 docker-compose.yml을 작성합니다.
3. 도커 컴포즈를 실행합니다.
4. http://127.0.0.1:50000에 접속한 후, 브라우저 화면을 새로고침했을 때 숫자가 증가하면 성공!

### 실습 2: 방명록 서비스 실행하기
다음과 같은 구성으로 docker-compose.yml 파일을 작성해보세요.


**프론트엔드**
| 정보  |  내용  |
|---|---|
| 이미지 | subicura/guestbook-frontend:latest |
| PORT 환경변수 | 서비스를 실행할 포트 |
| GUESTBOOK_API_ADDR 환경변수 | Backend 서버 주소 ex) backend:8000 |

**백엔드**
| 정보  |  내용  |
|---|---|
| 이미지 | subicura/guestbook-backend:latest |
| PORT 환경변수 | 서비스를 실행할 포트 |
| GUESTBOOK_DB_ADDR 환경변수 | DB 서버 주소 ex) mongodb:27017 |

**데이터베이스**
| 정보  |  내용  |
|---|---|
| 이미지 | mongo:4 |
| Listening Port | 27017 |
| 볼륨 설정 | /data/db |

```yaml
version: '3'

services:
  frontend:
    image: subicura/guestbook-frontend:latest
    ports:
      - "60081:8080"
    environment:
      - PORT=8080
      - GUESTBOOK_API_ADDR=backend:5000
    depends_on:
      - backend

  backend:
    image: subicura/guestbook-backend:latest
    environment:
      - PORT=5000
      - GUESTBOOK_DB_ADDR=mongodb:27017
    depends_on:
      - mongodb

  mongodb:
    image: mongo:4
    volumes:
      - mongo_data:/data/db

volumes:
  mongo_data: {}
```