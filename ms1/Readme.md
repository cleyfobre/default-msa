# Default MSA

### Registry

1. main class
```java
@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

2. application.yml
```yaml
server:
  port: 8761

spring:
  application:
    name: demo

eureka:
  #
  # 자기 보호 모드 (enable-self-preservation)
  # 개발 환경에서는 네트워크 불안정이 자주 발생하지 않으며, 신속한 테스트와 디버깅이 중요하다.
  # 따라서 자기 보호 모드를 비활성화하여 다운된 인스턴스가 즉시 레지스트리에서 제거되도록 하는 것이 좋다.
  # 반면, 운영 환경에서는 서비스 가용성이 중요하므로 자기 보호 모드를 활성화하여 네트워크 불안정 시에도 서비스가 정상적으로 작동하도록 해야 한다.
  # 
  # server:
  #   enable-self-preservation: false
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

3. build.gradle
```groovy
ext {
	set('springCloudVersion', "2024.0.0")
}

dependencies {
	implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
	}
}
```

### Gateway

1. main class
- Spring Cloud 2024.0.0 부터는 @EnableEurekaClient가 필요 없다.
```java
@SpringBootApplication
public class DemoGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGwApplication.class, args);
	}

}

```

2. application.yml
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka # Eureka 서버 주소 설정
  instance:
    lease-renewal-interval-in-seconds: 10 # 갱신 간격 (초)
    lease-expiration-duration-in-seconds: 30 # 만료 시간 (초)
```

3. build.gradle
```groovy
ext {
	set('springCloudVersion', "2024.0.0")
}

dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-gateway'
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
	}
}

```

