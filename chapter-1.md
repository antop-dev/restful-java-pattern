# REST: 태생의 기원

## REST<sup>`Representational State Transfer`</sup>

HTTP<sup>`HyperText Transfer Protocol`</sup> 메소드와 URI<sup>`Uniform Resource Identifier`</sup> 사용 등의 웹 표준을 준수하는 아키텍처 스타일<sup>`architectural style`</sup>로, 다음과 같은 기본 철학을 갖고 있다.

* 모든 리소스를 URI로 구별할 수 있다.
* 모든 리소스는 복수의 형태로 나타낼 수 있다.
* 모든 리소스는 HTTP 표준 메소드를 이용하여 접근/수정/생성/삭제할 수 있다.
* 서버에는 어떤 상태 정보도 갖고 있지 않다. (무상태<sup>`stateless`</sup> 서버)

## 리차드슨 성숙도 모델

[리차드슨 성숙도 모델]()<sup>`RMM, Richardson Maturity Model`</sup>은 레너드 리차드슨<sup>`Leonard Richardson`</sup>이 고안한 모델이다.

* [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)
* [What is the Richardson Maturity Model?](https://nordicapis.com/what-is-the-richardson-maturity-model/)


## 안전과 멱등석

* 안전한 메소드<sup>`safe methods`</sup>: 서버 측의 상태 정보를 변경하지 않는 메소드 (**캐시가 가능**)
* 멱등한 메소드<sup>`idempotent methods`</sup>: 몇 번을 호출되더라도 동일한 결과를 리턴하는 메소드

| Http Method | 안전 | 멱등석 |
|:--------|:--------:|:-------:|
| GET | ○ | ○ |
| HEAD | ○ | ○ |
| POST | × | × |
| PUT | × | ○ |
| DELETE | × | ○ |

## RESTful 서비스의 설계 원칙

아래의 원칙을 따르면 "**RESTful 하다**"라고 한다.

* 리소스 URI 결정: 어떤 명사<sup>`nouns`</sup>로 리소스를 나타낼지 정한다.
* 리소스 메소드 결정: CRUD에 해당하는 여러 가지 HTTP 메소드<sup>`verbs`</sup>를 사용한다.
* 리소스 표현형<sup>`representation`</sup> 결정: JSON<sup>`eXtensible Markup Languag`</sup>, XML<sup>`JavaScript Object Notation`</sup>, HTML<sup>`HyperText Markup Language`</sup>, 일반 텍스트<sup>`plain text`</sup> 중 어떻게 리소스를 표현할지 선택한다.

### API 구현

* [JAX-RS and Jersey](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jersey)
* [Exploring the Jersey Test Framework](https://www.baeldung.com/jersey-test)
