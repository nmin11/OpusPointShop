## Version

- Java 17 (LTS)
- Spring Boot 2.7.0 (LTS)
- h2 Database 2.1.212 (LTS)

<br/>

## h2 데이터베이스 Setup

- 버전 : Spring Boot LTS 버전과 호환되는 2.1.212 버전 사용
- 초기 연결 방법

![h2 최초 접속](https://user-images.githubusercontent.com/75058239/169972431-22b4bcfb-a66a-46a0-beb1-dbb18991bf25.png)

- 이후 원격 접속 방법

![h2 원격 접속](https://user-images.githubusercontent.com/75058239/169972514-9f26f5dd-e898-4a40-9ecd-729aea9dd675.png)

- h2 db 파일을 `~/h2/db` 디렉토리에 두었음

<br/>

## ERD

![ERD](https://user-images.githubusercontent.com/75058239/170810957-7af71cf3-0e1a-49a3-8f96-120d56e75b8d.png)

<br/>

## Thymeleaf view

- index page

![index page](https://user-images.githubusercontent.com/75058239/170810104-20e5ff93-5c46-4671-bae7-f147867191a6.png)

- cart page

![cart page](https://user-images.githubusercontent.com/75058239/170870271-d286adf7-1de7-4111-90ba-ae0d0e1e06bc.png)

## 세부 구현 사항

- h2 실행 및 Spring Boot 실행 후 `localhost:8080` 에서 thymeleaf 로 구현된 렌더링 페이지에서 간단한 테스트가 가능합니다.
- `ddl-auto: create` 옵션을 적용했기 때문에 실행할 때마다 데이터가 초기화되는 환경이지만 `InitDB` 파일을 통해 초기 데이터를 바로 삽입하도록 해두었습니다.
- 예외 처리는 직접적으로 `throw` 하기 보다는 예외가 발생할 것으로 예상되는 포인트마다 다른 Response Entity 를 리턴하도록 해주었습니다.
  - 구현한 예외 케이스들 : 찾는 회원 없음, 찾는 상품 없음, 이미 존재하는 회원, 이미 존재하는 상품, 돈 없음, 포인트 없음
- 테스트 케이스는 아직 제가 테스트 방법론에 익숙치 않아서 구현하지 못했습니다. 추후 반드시 학습할 계획입니다.
