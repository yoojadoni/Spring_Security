@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
  }
}
