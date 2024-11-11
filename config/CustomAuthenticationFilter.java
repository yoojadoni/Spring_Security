import config/entity/Member;
import org.apache.commons.lang3.StringUtils;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  
  private final ObjectMapper objectMapper;
  
  public CustomAuthenticationFilter() {
    super(new AntPathRequestMatcher("/login"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
    // POST인지
    if(!request.getMethod().equalsIgnoreCase("POST")){
      throw new IllegalStateException("Authentication Only Post");
    }

    Member member = objectMapper.readValue(request.getReader(), Member.class)

    if(StringUtils.isBlank(member.getUserId()){
      throw new IllegalStateException("UserId is Not Empty");
    }
    try{
      // 토큰 생성
      CustomAuthenticationToken token = new CustomAuthenticationToken(member.getUserId(), member.getPassword());
      /* token에 더 필요한 데이터가 있는 경우 사용
      AuthenticationDetails details = new AuthenticationDetails();
      details.setUserIp("127.0.0.1");
      token.setDetails(details)
      */
      // 인증처리
      Authentication authentication = getAuthenticationManager().authenticate(token);
      return authentication;      
    }catch (CustomAuthenticationException e){
      throw e;
    }catch (Exception e){
      log.error("로그인 인증 처리중 오류 : {}", e.getMessage());
      throw new CustomAuthenticationException("9000", "로그인 처리 중 오류");
    }
  }
}
