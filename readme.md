# ���̹���ϵͳ�Ĵ

## ��Ŀ����ͼ

![image-20200421204054058](C:\Users\kangb\AppData\Roaming\Typora\typora-user-images\image-20200421204054058.png)

## ��Ŀ����ջ

### ǰ��

Vue  https://cn.vuejs.org/

Vue-router  https://router.vuejs.org/

Element-UI  https://element.eleme.cn/

Axios  http://www.axios-js.com/

Echarts  https://www.echartsjs.com/

### ���

SpringBoot  https://spring.io/projects/spring-boot/

MyBatis  http://mybatis.org/

MYSQL  https://www.mysql.com/

Redis  http://www.redis.cn/

Elastic-Search  https://www.elastic.co/cn/

Shiro  http://shiro.apache.org/

## ǰ����Ŀ�ṹͼ

![image-20200421231157231](E:\typora\data\image-20200421231157231.png)

### ��װ���ּ�vue-cli

### ������Ŀ

1��ʹ��vue create project-name(mange) ������Ŀ���ڶ���ʹ��vue ui ����򿪿��ӻ����棬��װ���

![image-20200421201957414](E:\typora\data\image-20200421201957414.png)

*���ѡ���赼��*

2����װ����

![image-20200421202207468](E:\typora\data\image-20200421202207468.png)

## ��̨��Ŀ�ṹͼ

![image-20200421203714399](E:\typora\data\image-20200421203714399.png)

### pom

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.manage</groupId>
    <artifactId>voyage-manage</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>voyage-manage</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--ͨ��ģ��-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--swagger-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
        <!--redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!--MyBatis��ҳ���-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.10</version>
        </dependency>
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
        <!--tk.mybatis-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.1.5</version>
        </dependency>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>
        <!--druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.20</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

### application.yml

```xml
server:
  port: 8090

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/manage?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    dbcp2:
      initial-size: 5
      min-idle: 5
      max-wait-millis: 60000
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 3600000
      validation-query: SELECT 1 FROM DUAL
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false

mybatis:
  mapper-locations:
    - classpath:mapper/*.xml

redis:
  host: localhost
  database: 0
  port: 6379
  password:
  jedis:
    pool:
      max-active: 8
      max-wait: -1ms
      max-idle: 8
      min-idle: 0
  timeout: 3000ms

logging:
  level:
    com.manage.mapper: debug

#spring:
#  data:
#    mongodb:
#      host: localhost
#      port: 27017
#      database: manage
#data:
#  elasticsearch:
#    repositories:
#      enabled: true
#    cluster-nodes: 127.0.0.1:9300
#    cluster-name: elasticsearch

```



### controller

### service

#### serviceImpl

### mapper

### entity

### model

### common

### config

### utils

## ���ݿ�����

## �ȹ��Ŀ�

### ���ݿ������쳣

```java
2020-04-21 20:58:37.097 ERROR 13812 --- [reate-997919575] com.alibaba.druid.pool.DruidDataSource   : create connection SQLException, url: jdbc:mysql://localhost:3306/manage?useUnicode=true&characterEncoding=UTF8, errorCode 0, state 01S00

java.sql.SQLException: The server time zone value '?��???????' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
Caused by: com.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value '?��???????' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) 

```

### ����취��

�����ݿ����Ӹ�Ϊ��

```javascript
jdbc:mysql://localhost:3306/manage?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC
```

��ǰ�ǣ�

```javascript
jdbc:mysql://localhost:3306/manage?useUnicode=true&characterEncoding=UTF8
```

�����޸�mysql��ϵͳʱ����[�ο�����](https://blog.csdn.net/qq_28018283/article/details/80109290)

## ���ܷ���

### ��¼���ǳ�

#### �߼�

1���ڵ�¼ҳ�������û���������

2�����ú�̨�ӿ���֤����̨�ȴ�redis�л�ȡ�û�����redis�д��ڸ��û���У����Ϣ���粻���ڣ���mysql�л�ȡ�û�����У����Ϣ�������û��浽redis��

3����¼�ɹ��Ժ�������Ŀ����ҳ

4���û��ǳ���������¼ҳ��

#### ������

Http����״̬�ģ�Ҫ�����û��ĵ�¼״̬��Ŀǰ�����ַ�ʽѡ�񣬵�һ����ʹ��cookie-session�����о���ʹ��jwt��token��֤���ͻ���ÿ�����󶼴�һ������ͷ������token��������ͷ��