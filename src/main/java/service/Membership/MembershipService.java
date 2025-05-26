package service.Membership;

import domain.Membership.MembershipVO;
import java.util.List;

public interface MembershipService {
    void joinMember(MembershipVO member);              // 회원 등록
    MembershipVO getMemberInfo(String phoneNum);       // 회원 정보 조회
    void addPoint(String phoneNum, int point);         // 포인트 추가
    List<MembershipVO> getAllMembers();                // 전체 회원 조회
}
