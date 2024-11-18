# Spring boot 3.2.6 버전에서의 Spring_Security 세팅의 예시

* AuthenticationEntryPoint
인증이 안된 익명의 사용자가 인증이 필요한 엔드포인트로 접근하게 된다면 Spring Security의 기본 설정으로는 HttpStatus 401과 함께 스프링의 기본 오류페이지를 보여준다.
HttpStatus 401 Unauthorized는 사용자가 인증되지 않았거나 유효한 인증 정보가 부족하여 요청이 거부된 것을 말한다.
기본 오류 페이지가 아닌 커스텀 오류 페이지를 보여준다거나, 특정 로직을 수행 또는 JSON 데이터 등으로 응답해야 하는 경우, 우리는 AuthenticationEntryPoint 인터페이스를 구현하고 구현체를 시큐리티에 등록하여 사용할 수 있다.
AuthenticationEntryPoint 인터페이스는 인증되지 않은 사용자가 인증이 필요한 요청 엔드포인트로 접근하려 할 때, 예외를 핸들링 할 수 있도록 도와준다.

* AccessDeniedHandler
인증이 완료되었으나 해당 엔드포인트에 접근할 권한이 없다면, 403 Forbidden 오류가 발생한다.
이 역시 스프링 시큐리티는 기본적으로 스프링의 기본 오류 페이지를 응답한다.
HttpStatus 403 Forbidden은 서버가 해당 요청을 이해하였으나, 사용자의 권한이 부족하여 요청이 거부된 상태를 말한다.
이를 커스텀하기 위해서는 AccessDeniedHandler 인터페이스를 구현하면 된다.
