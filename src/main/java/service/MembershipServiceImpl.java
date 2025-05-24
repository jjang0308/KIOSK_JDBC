package service;

import dao.MembershipDao;
import domain.MembershipVO;

import java.util.List;

public class MembershipServiceImpl implements MembershipService {
    private final MembershipDao membershipDao;

    public MembershipServiceImpl(MembershipDao membershipDao) {
        this.membershipDao = membershipDao;
    }

    @Override
    public void joinMember(MembershipVO member) {
        membershipDao.insert(member);
    }

    @Override
    public MembershipVO getMemberInfo(String phoneNum) {
        return membershipDao.findByPhone(phoneNum);
    }

    @Override
    public void addPoint(String phoneNum, int point) {
        MembershipVO member = membershipDao.findByPhone(phoneNum);
        if (member != null) {
            int newPoint = member.getPoint() + point;
            membershipDao.updatePoint(phoneNum, newPoint);
        }
    }

    @Override
    public List<MembershipVO> getAllMembers() {
        return membershipDao.getAllMembers();
    }
}
