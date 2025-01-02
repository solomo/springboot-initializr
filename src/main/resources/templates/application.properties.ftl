#master
spring.datasource.dynamic.primary=master
spring.datasource.dynamic.datasource.master.url=${datasource.mysql.url}
spring.datasource.dynamic.datasource.master.username=${datasource.mysql.username}
spring.datasource.dynamic.datasource.master.password=${datasource.mysql.password}
spring.datasource.dynamic.datasource.master.driver-class-name=com.mysql.cj.jdbc.Driver
#hikari
spring.datasource.dynamic.hikari.minimum-idle=10
spring.datasource.dynamic.hikari.idle-timeout=180000
spring.datasource.dynamic.hikari.maximum-pool-size=30
spring.datasource.dynamic.hikari.auto-commit=true
spring.datasource.dynamic.hikari.pool-name=MyHikariCP
spring.datasource.dynamic.hikari.max-lifetime=300000
spring.datasource.dynamic.hikari.connection-timeout=30000
spring.datasource.dynamic.hikari.connection-test-query=SELECT 1
#page
pagehelper.helper-dialect=mysql
pagehelper.reasonable=true
pagehelper.support-methods-arguments=true
pagehelper.params=count==countSql
pagehelper.page-size-zero=true
mybatis.configuration.mapUnderscoreToCamelCase=true
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

########################################################
###FREEMARKER (FreeMarkerAutoConfiguration)
########################################################
spring.jmx.enabled=false
spring.freemarker.allow-request-override=true
spring.freemarker.cache=false
spring.freemarker.check-template-location=true
spring.freemarker.charset=UTF-8
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=false
spring.freemarker.expose-session-attributes=false
spring.freemarker.expose-spring-macro-helpers=false
spring.freemarker.suffix=.ftl
spring.freemarker.template-loader-path=classpath:/templates/
spring.freemarker.settings.number_format=0.##
spring.mvc.favicon.enable=false
spring.freemarker.request-context-attribute=request
spring.web.resources.chain.strategy.fixed.enabled=true
spring.web.resources.chain.strategy.fixed.paths=/js/**,/v1.0.0/**
spring.web.resources.chain.strategy.fixed.version=v1.0.0
#redis
spring.data.redis.host=${redis.host}
spring.data.redis.password=${redis.password}
spring.data.redis.port=${redis.port}
spring.data.redis.database=${redis.database}
#spring
server.port=8080
server.servlet.context-path=/
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain
server.compression.min-response-size=2048
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
spring.mvc.converters.preferred-json-mapper=gson

#logger
logging.config=classpath:logback-spring.xml
logging.file.path=./logs
#mybatis
mybatis-plus.mapper-locations=classpath*:mapper/*Mapper.xml
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
mybatis-plus.configuration.use-generated-keys=true

spring.cache.type=REDIS
spring.redis.lettuce.pool.max-total=8
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
#shutdown
management.endpoint.shutdown.enabled=true
management.endpoints.web.exposure.include=shutdown
#springdoc
springdoc.swagger-ui.version=3.0.0
springdoc.swagger-ui.use-root-path=true