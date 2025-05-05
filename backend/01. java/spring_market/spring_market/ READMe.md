* 모듈 관련

  * Spring Market Application
      ├── settings.gradle(Gradle에게 어떤 하위 프로젝트(모듈)들이 이 빌드에 포함되는지 알려주는 역할)
      ├── build.gradle(루트 프로젝트 기준 하위의 공통 dependency)
      ├── module-crosscut(aop 횡단관심사 및 , ControllerAdvice 구성)
      │ ├── 전체 프로젝트에서 사용하는 AOP 및 ControllerAdvice 구성
      │ ├── build.gradle(각 모듈의 dependency)
      ├── module-common(공통 유틸리티 모듈)
      │ ├── 전체 프로젝트에서 공통적으로 사용하는 유틸리티, 예외 처리, 공통 상수, 응답 코드, 공통 로직 등을 모아두는 모듈
      │ ├── build.gradle(각 모듈의 dependency)
      ├── module-domain(도메인 엔티티 모듈)
      │ ├── 비즈니스 도메인에 해당하는 엔티티, 도메인 서비스, 리포지토리 인터페이스 등 핵심 비즈니스 로직과 관련된 코드를 분리
      │ ├── build.gradle(각 모듈의 dependency)
      ├── module-infra(인프라스트럭처 모듈)
      │ ├── 외부 시스템 연동(데이터베이스, 메시징, 파일, 외부 API 등)과 같은 인프라 관련 코드를 담당
      │ ├── build.gradle(각 모듈의 dependency)
      ├── module-security(스프링 시큐리티 모듈)
      │ ├── 인증 보안 연동 코드를 담당
      │ ├── build.gradle(각 모듈의 dependency)
      ├── module-server(메인 실행 모듈)
      │ ├── 실제로 서버를 실행하는 모듈로, 컨트롤러, 서비스, 애플리케이션 실행 진입
      │ ├── build.gradle(각 모듈의 dependency)