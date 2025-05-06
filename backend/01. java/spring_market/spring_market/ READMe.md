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

  * spring market table 연관관계
    * 테이블명	                    역할/설명	                                                주요 관계(→는 참조)
      User	                        주문을 생성하는 고객(회원) 정보	                            1:N → Order
      Order	                        주문  1건, 주문자, 주문일시, 상태, 배송지 등 포함	                N:1 → User
                                                                                            1:N → OrderItem
      OrderItem	                    주문 내 상품 1개(상품명, 옵션 요약, 수량, 가격 등)	            N:1 → Order
                                                                                            N:1 → Product
                                                                                            1:N → OrderItemOption
                                                                                            1:N → OrderItemAdditionalProduct
      OrderItemOption	            주문상품의 옵션 내역(색상, 사이즈 등)	                        N:1 → OrderItem
      OrderItemAdditionalProduct	주문상품의 추가상품 내역(보증, 액세서리 등)	                    N:1 → OrderItem
      Category	                    상품의 분류(대분류/중분류/소분류 등), 계층형 구조	                1:N → Product
                                                                                            1:N → Category (자기참조)
      Product	                    판매 상품(상품명, 설명, 가격, 재고 등)	                        N:1 → Category
                                                                                            1:N → ProductOptionGroup
                                                                                            1:N → AdditionalProduct
                                                                                            1:N → OrderItem
      ProductOptionGroup	        상품 옵션의 그룹(예: 색상, 사이즈 등)	                        N:1 → Product
                                                                                            1:N → ProductOption
      ProductOption	                옵션 그룹에 속한 실제 옵션 값(예: 빨강, 파랑, 소, 중, 대 등)	    N:1 → ProductOptionGroup
      AdditionalProduct	            상품과 함께 구매 가능한 추가상품(예: 보증연장, 액세서리 등)	        N:1 → Product
   
    * 흐름도
      * 상품 주문
      User (1) ──< (N) Order (1) ──< (N) OrderItem (1) ──< (N) OrderItemOption
                                            │
                                            └──< (N) OrderItemAdditionalProduct
      * 상품(단일 상폼, 상품 옵션, 추천 상품)
      Category (1) ──< (N) Product (1) ──< (N) ProductOptionGroup (1) ──< (N) ProductOption
        │                     │
        │                     └──< (N) AdditionalProduct
        └──< (N) Category (자기참조, 계층형)
      * 주문서 상품정보
      OrderItem (N) ──> (1) Product


  * spring market API
    * 카테고리는 있다는 가정(*)
    * 상품 등록/수정/조회 API
    * 주문 관련 API