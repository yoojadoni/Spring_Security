@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Member {
  @ToString.Include
  private String userId;
  @ToString.Include
  private String userName;
}

