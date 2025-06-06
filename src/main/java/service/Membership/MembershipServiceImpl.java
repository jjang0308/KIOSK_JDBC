package service.Membership;

import dao.Membership.MembershipDao;
import domain.Membership.MembershipVO;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipDao membershipDao;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public MembershipVO getMemberInfo(String phone) {
        return membershipDao.findByPhone(phone);
    }

    @Override
    public void joinMember(MembershipVO member) {
        membershipDao.insert(member);
    }

    @Override
    public void addPoint(String phone, int point) {
        membershipDao.updatePoint(phone, point);
    }

    // ✅ 사용자 흐름 포함: 전화번호로 멤버 찾고 없으면 등록
    public MembershipVO memberInfo() {
        System.out.print("전화번호를 입력해주세요: ");
        String phone = scanner.nextLine();

        MembershipVO member = getMemberInfo(phone);
        if(phone.length() < 11 ){
            System.out.println("전화번호 양식에 맞지 않아 결제를 취소합니다.");
            System.out.println("다시 결제해주시길 바랍니다.");
        }

        else if (member == null) {
            System.out.println("등록된 회원이 없습니다. 새로 등록합니다.");
            System.out.print("닉네임을 입력해주세요: ");
            String nickname = scanner.nextLine();

            member = MembershipVO.builder()
                    .phone_num(phone)
                    .nickname(nickname)
                    .point(0)
                    .build();

            joinMember(member);
            System.out.println("회원 등록이 완료되었습니다.");
        }

        return member;
    }

    // ✅ 사용자 흐름 포함: 포인트 적립
    public void handlePayment(String phone) {
        MembershipVO member = getMemberInfo(phone);

        System.out.printf("닉네임: %s | 현재 포인트: %d점\n", member.getNickname(), member.getPoint());

        int totalAmount = 5000;                 //List에서 총 금액을 받오거나 food 선택에 따라 금액을 하나씩 더할 예정
        int usePoint = 0;
        while (true) {
            System.out.print("포인트를 사용하시겠습니까? (y/n): ");
            String usePointAnswer = scanner.nextLine().trim().toLowerCase();

            if (usePointAnswer.equals("y")) {

                if (usePoint > member.getPoint()) {
                    System.out.println("포인트 부족");
                    return;
                }
                if (usePoint > totalAmount) {
                    System.out.println("결제 금액보다 많이 사용할 수 없습니다.");
                    return;
                }

                addPoint(phone, -usePoint);
                System.out.printf("%d포인트 사용됨\n", usePoint);
                break; // 사용 완료 시 루프 종료

            } else if (usePointAnswer.equals("n")) {
                break; // 사용하지 않으면 바로 종료

            } else {
                System.out.println("y 또는 n만 입력해주세요.");
            }
        }

        System.out.println("숫자만 입력해주세요.");

        int pointToAdd = totalAmount / 10;
        addPoint(phone, pointToAdd);
        System.out.printf("%d원 결제 완료. %d포인트 적립됨\n", totalAmount, pointToAdd);
    }
}

