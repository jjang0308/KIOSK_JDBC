package dao.Membership;

import domain.MembershipVO;


import java.util.*;

public interface MembershipDao {
    void insert(MembershipVO member);
    MembershipVO findByPhone(String phoneNum);
    void updatePoint(String phoneNum, int point);
    List<MembershipVO> getAllMembers();
}
