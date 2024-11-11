
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
    String userId   = authentication.getName();
    String password = (String) authentication.getCredentials();
    // (DB) 유저 정보 조회
    CustomUserDetail customUserDetail = (CustomUserDetail) userDetailsService.loadUserByUsername(loginId);
    if(customUserDetail == null){
       throw new CustomAuthenticationException("401", "User Not Exists");
    }
    // 비밀번호 검증
    if(!passwordEncoder.passwordEncoder().matches(password, customUserDetial.getPassword())){
      throw new CustomAuthenticationException("401", "Invalid password!");
    }
    return new CustomAuthenticationToken(customUserDetail, null, customUserDetail.getAuthorities());
  }
  
}
