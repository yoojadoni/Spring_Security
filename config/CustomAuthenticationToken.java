
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

  private final Object principal;
  private Object credentials;

  public CustomAuthenticationToken(Object principal, Object credentials){
    super(null);
    this.principal = principal;
    this.credentials = credentials;
  }

  @Override
  public Object getCredentials(){
    return this.credentials;
  }

  @Override
  public Object getPrincipal(){
    return this.principal;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated){
    Assert.isTrue(!isAuthenticated, "");
    super.setAuthenticated(false);
  }

  @Override
  public void eraseCredentials(){
    super.eraseCredentials();
    this.credentials = null;
  }

}
