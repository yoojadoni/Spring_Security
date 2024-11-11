@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Member {
  @ToString.Include
  private String userId;
  private String password;
  @ToString.Include
  private String userName;
}

