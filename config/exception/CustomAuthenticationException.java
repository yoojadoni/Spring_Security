@Getter
public class CustomAuthenticationException extends AuthenticationException {
  private String code;
  private String message;

  public CustomAuthenticationException(String code, Strimg message) {
    super(message);
    this.code = code;
    this.message = message;
  }
}
