
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Data
public class CustomUserDetail implements UserDetails {
  private String memberkey;
  private String email;
  private String userName;
  private String password;
  private String phone;
  private String authType;
  private String role = "ROLE_USER";
}

