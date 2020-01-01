# 리소스 설계

* REST 리소스 패턴
* 콘텐츠 협상
* 엔티티 제공자와 여러가지 표현형
* API 버저닝
* 응답코드와 RES 패턴

## REST 리소스 패턴

표준적인 형태의 REST 요청/응답 패턴이다.

![Imgur](https://i.imgur.com/b96yfeN.png)

## 콘텐츠 협상<sup>`contents negotiation`</sup>

동일한 `URI`의 리소스를 여러 가지 표현형으로 제공하여, 클라이언트가 원하는 하나를 선택할 수 있게 하는 것을 말한다.

* HTTP 헤더를 이용: 클라이언트는 서버에 자신이 처리할 수 있는 미디어 타입을 명시하고 `Accept` 헤더에 그 우선 순위를 나열한다.
* URL 패턴을 이용: 리소스 `URL`의 확장자를 보고 리소스 표현형을 결정하는 것이다.

## API 버저닝<sup>`versioning`</sup>

API 버저닝의 목적은 리소스의 종단점과 주소를 정의하고 여기에 버전을 부여하는 것이다. 애플리케이션이 운영하기 전까지 `API` 버전은 계속 업데이트 되면서, 일부 구버전 API는 폐기<sup>`deprecated`</sup>되어야 한다.

* `URI`에 지정

    ```
    http://api.foo.com/coffees/1234
    http://api.foo.com/v2/coffees/1234
    ```

    클라이언트가 구버전 API에 접속하면 HTTP 에러 코드를 리턴하여 최신 버전 `API`로 옮겨갈 수 있도록 조치해야 한다.
    
    * `301` Moved Permanently
    * `302` Found

* 요청 파라미터에 지정

    ```
    http://api.foo.com/coffees/1234?version=v2
    ```
    
    응답이 캐시되지 않는다.<br>
    소스 코드에서 흐름을 분기시켜야한다. (직관적 `x`, 유지보수 `x`)

* `Accept` 헤더에 지정

    ```
    Accept: application/vnd.foo-v1+json
    ```
    
    `vnd`는 벤더 고유<sup>`vendor-specific`</sup>의 MIME 타입니다. 이렇게 하면 더 이상 `URL`에 버전 정보를 넣을 필요가 없다.

## 응답 코드와 REST 패턴

* [HTTP 상태 코드 - 위키백과](https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C)
* [HTTP 상태 코드 - MDN](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)
* [HTTP/응답 코드 - 나무위키](https://namu.wiki/w/HTTP/%EC%9D%91%EB%8B%B5%20%EC%BD%94%EB%93%9C)

요청에 대한 응답 코드는 HTTP 표준으로 이미 정해져 있다.

* 성공: `200` `201` `204` `202`
* 리다이렉션: `301` `302`
* 클라이언트 에러: `401` `404` `406` `415`
* 서버 에러: `500` `503`

## References

* [Explore Jersey Request Parameters](https://www.baeldung.com/jersey-request-parameters)
* [Representations and Responses](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/representations.html)
* [Twitter Developer - Versions](https://developer.twitter.com/en/docs/ads/general/overview/versions.html)