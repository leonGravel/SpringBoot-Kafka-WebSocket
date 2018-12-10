# SpringBoot-Kafka-WebSocket


[![Build Status](https://www.travis-ci.org/leonGravel/SpringBoot-Kafka-WebSocket.svg?branch=master)](https://www.travis-ci.org/leonGravel/SpringBoot-Kafka-WebSocket)

一个springboot集成kafka和websocket的小例子

### 快速开始

1. 下载项目

```
git clone https://github.com/leonGravel/SpringBoot-Kafka-WebSocket.git
```

2. 将项目导入IDE，运行`Application.java` 

3. 访问`http://localhost:8888/index`即可查看效果

    * 点击连接按钮，即可连接websocket
    * 点击发送按钮，即可将文本框内消息发送到Kafka
    * 这里可以多开窗口进行测试

### TIPS

本项目使用的 `Kafka` 以及 `zookeeper` 是我部署在自己的服务器上的，请勿恶意使用。
我是使用docker-compose进行部署。具体文件如下：

```
version: '3'

services:
  zookeeper:
    image: wurstmeister/zookeeper
    restart: unless-stopped
    hostname: zookeeper
    ports:
      - "2181:2181"
    container_name: zookeeper

  # kafka version: 1.1.0
  # scala version: 2.12
  kafka:
    image: wurstmeister/kafka
    restart: always
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 132.232.103.230
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper:2181"
      KAFKA_BROKER_ID: 1
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_CREATE_TOPICS: "stream-in:1:1,stream-out:1:1"
    depends_on:
      - zookeeper
    container_name: kafka
    
```
`docker-compose up -d` 即可完成 `Kafka` 以及 `zookeeper` 的部署。然后再项目配置文件里面配置上 `kafka` 的地址即可。
