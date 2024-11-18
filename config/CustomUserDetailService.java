
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailService {

  private final MemberService memberService;
  
  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    // 사용자 정보 조회
    Member member = memberService.getMember(new MemberInfo(userId)).orElseThrow(() -> new UsernameNotFoundException("User Not Found")));
    CustomUserDetail customUserDetail = new CustomUserDetail();
    customUserDetail.setMemberKey(member.getMemberKey());
    customUserDetail.setUserName(member.getName());
    customUserDetail.setPassword(member.getPassword());
    customUserDetail.setPhone(member.getPhone());
    return customUserDetail;
  }
}
