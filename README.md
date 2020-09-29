# Security 프로젝트

## 개요
Spring Security를 이용하여 프로젝트를 진행한다.  
도메인은 '사내 계정 관리'로 정해서 개발할 예정이다.  
이번에는 Reference를 보면서 해당하는 기능들을 하나하나 이해하는 과정을 거친다.  
공부는 Reference로 하되, 기존 Servlet 기반 Application을 구현하고,  
다음으로 Netty 기반 Webflux에도 적용해본다.  
마지막으로는 Spring cloud 환경에서도 어떻게 적용할 수 있을지 알아본다.

## 설명
- JWT는 REST환경에서의 인증 환경을 구성하기 위하여 사용하였다.  
  그래서 Session과 스프링 기본 로그인 기능, CSRF 체크를 끄고 사용하였다.  

## Dependency
```
- spring-boot-starter-oauth2-client
- spring-boot-starter-security
- spring-boot-starter-web
- h2(내장 DB): CommandlineRunner를 이용 어플리케이션 구동시 초기 유저 아이디 몇 개 기본 추가
```

## 할 일
[x] 기본적인 아이디/비밀번호 인증(Json Web Token)  
[ ] OAuth 2.0 인증  
[ ] 관련 테스트 작성  
[ ] Webflux(Netty 기반)에서도 동일 기능 구성  
[ ] Spring Cloud 환경에서의 Security 구성 공부 및 개발  
   
## USER 정보 정리
- 아이디: id
- 비밀번호: password
- 이메일: email
- 사진 경로: photoPath
- 주소: address
- 소속 팀: team
- 입사일: workStartDate

## 계정 관리 추가 기능
- 조회 기능(DB 쿼리) 
- 비밀번호 변경 기능
- 다른 정보 변경 기능

## JWT 관련 클래스
- ClientController.java: 로그인/로그아웃/조회/수정 기능이 있는 컨트롤러
- Client.java: UserDetails 인터페이스를 구현한 도메인 클래스이다. 내부적으로 Authority/Role 설정을 해주어야 한다.
- DefaultClientService: IUClient 정보를 DB에서 직접 가져오는 서비스 클래스. UserDetailsService를 구현한다.
- JwtAuthenticationFilter: Spring Security에 꽂아넣을 Servlet 필터
- JwtTokenProvider: JWT 토큰에 관련된 변환/검증을 담당하는 클래스
- SecurityConfig: Spring Security 전반적인 설정을 담당하는 대표 Configuration 클래스


### 참고
- jwt 인증 관련: https://webfirewood.tistory.com/m/115?category=672592