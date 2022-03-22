spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${jdbc_url}
    username: ${jdbc_username}
    password: ${jdbc_password}


mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: ${entity_path}.*