
import config/CustomPasswordEncoder;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

  // 유저 정보 조회
  private final MemberService memberService;
  // 패스워드 암호화
  private final CustomPasswordEncoder passwordEncoder;

  private final CustomUserDetailService userDetailService;

  private final ObjectMapper objectMapper;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userId = authentication.getName();
    String password = 
  }
  
}
