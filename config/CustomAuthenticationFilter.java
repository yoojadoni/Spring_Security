
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public CustomAuthenticationFilter() {
    super(new AntPathRequestMatcher("/login"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
    // POST인지
    if(!request.getMethod().equalsIgnoreCase("POST")){
      throw new IllegalStateException("Authentication Only Post");
    }

    
  }
}
