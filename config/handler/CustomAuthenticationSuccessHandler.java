

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
    HttpSession session = request.getSession();
    session.setAttribute("userId", customUserDetail.getUserId());
    session.setMaxInactiveInterval(600); // 10분
    response.setStatus(HttpStatus.OK.value());
    //response 객체
    HashMap<String, Object> map = new HashMap<>();
    map.put("code" , "0000");
    map.put("message", "성공");
    objectMapper.writeValue(response.getWrite(), map);
  }
}
