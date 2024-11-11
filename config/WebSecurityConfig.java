import config/handler/CustomAuthenticationSuccessHandler;
import config/handler/CustomAuthenticationFailureHanlder;
import config/handler/customAccessDeniedHandler;
import config/CustomLoginAuthenticatonEntryPoint;
import config/CustomPasswordEncoder;
import config/SessionAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
  private final CustomAuthenticationFailureHanlder customAuthenticationFailureHanlder;
  private final CustomLoginAuthenticatonEntryPoint customLoginAuthenticatonEntryPoint;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final SessionAuthFilter sessionAuthFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement( session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        )
        //인가
        .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(
                                  new AntPathRequestMatcher("권한설정을 원하는 URL")
                                ).hasRole("USER")
                                .anyRequest().permitAll()
        )
        .addFilterBefore(sessionAuthFilter, SessionManagementFilter.class) //세션 확인
        .addFilterBefore(customAutenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(config -> config
                                      .authenticationEntryPoint(customLoginAuthenticatonEntryPoint)
                                      .accessDeniedHandler(customAccessDeniedHandler)
        )
    ;
    return http.build();
  }

  // 비밀번호 암호화
  @Bean
  public PasswordEncoder passwordEncoder(){
    return CustomPasswordEncoder.passwordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
    customAuthenticationFilter.setAuthenticationManager(authenticationManager());
    customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
    customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHanlder);
    customAuthenticationFilter.setSecurityContextRepository(
        new DelegatingSecurityContextRespository(
          new RequestAttributeSecurityContextRepository()
          , new HttpSessionSecurityContextRepository()
      )
    );

    return customAuthenticationFilter;
  }


}
