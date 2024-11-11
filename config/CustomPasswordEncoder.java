
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
* pbkdf2 암호화
**/
@Component
@RequiredArgsConstructor
import class CustomPasswordEncoder {
  public static PasswordEncoder passwordEncoder() {
    String prefix = "prefix";
    PasswordEncoder current = new Pbkdf2PasswordEncoder("암호화키", 16, 185000, 256);
    PasswordEncoder upgraded = Pbkdf2PasswordEncoder.defaultsForSpringSecurty_v5_8();
    DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder(prefix, Map.of(prefix, upgraded));
    delegating.setDefaultPasswordEncoderForMatches(current);
    return delegating;
  }
}
