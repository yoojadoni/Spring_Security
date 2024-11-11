
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationFailure(HttpServletRequest reqeust, HttpServletResponse response, AuthenticationException e) throws IOException{
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setConTentType(MediaType.APPLICATION_JSON_VALUE);//응답 contentType
    HashMap<String, Object> map = new HashMap<>();

    if(e instanceof CustomAuthenticationException){
      map.put("code" , (CustomAuthenticationException) e).getCode());
      map.put("message, (CustomAuthenticationException) e).getMessage());
    }

    objectMapper.writeValue(response.getWriter(), map);
  }  

}
