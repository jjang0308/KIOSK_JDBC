package app;

import dao.Membership.MembershipDao;
import dao.Membership.MembershipDaoImpl;
import dao.Payment.PaymentDao;
import dao.Payment.PaymentDaoImpl;
import config.JDBCUtil;
import domain.Membership.MembershipVO;
import service.Membership.MembershipService;
import service.Membership.MembershipServiceImpl;
import service.Payment.PaymentService;
import service.Payment.PaymentServiceImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class KioskApp {
    private final Scanner scanner = new Scanner(System.in);
    private final MembershipService membershipService;
    private final PaymentService paymentService;

    public KioskApp(MembershipService membershipService, PaymentService paymentService) {
        this.membershipService = membershipService;
        this.paymentService = paymentService;
    }

    public void run() {
        while (true) {
            System.out.println("\n========== [키오스크 결제 흐름] ==========");
            System.out.print("전화번호를 입력해주세요 (종료: 0): ");
            String phone = scanner.nextLine();

            if (phone.equals("0")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            MembershipVO member = membershipService.getMemberInfo(phone);
            if (member == null) {
                System.out.println("등록된 회원이 없습니다. 새로 등록합니다.");
                System.out.print("닉네임을 입력해주세요: ");
                String nickname = scanner.nextLine();

                member = MembershipVO.builder()
                        .phone_num(phone)
                        .nickname(nickname)
                        .point(0)
                        .build();

                membershipService.joinMember(member);
                System.out.println("회원 등록이 완료되었습니다.");
            }

            // 결제 수단 선택
            List<String> paymentTypes = paymentService.getAllPaymentTypes();
            System.out.println("\n[결제 수단 선택]");
            for (int i = 0; i < paymentTypes.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, paymentTypes.get(i));
            }
            System.out.print("결제 수단 번호 선택: ");
            int paymentChoice;
            try {
                paymentChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
                continue;
            }

            if (paymentChoice < 1 || paymentChoice > paymentTypes.size()) {
                System.out.println("유효하지 않은 선택입니다.");
                continue;
            }

            String selectedPayment = paymentTypes.get(paymentChoice - 1);
            System.out.println("선택한 결제 수단: " + selectedPayment);

            // 포인트 사용 여부
            System.out.printf("현재 포인트: %d점\n", member.getPoint());
            System.out.print("포인트를 사용하시겠습니까? (y/n): ");
            String usePointAnswer = scanner.nextLine();

            int usePoint = 0;
            if (usePointAnswer.equalsIgnoreCase("y")) {
                System.out.print("사용할 포인트를 입력해주세요: ");
                try {
                    usePoint = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("숫자만 입력해주세요.");
                    continue;
                }

                if (usePoint > member.getPoint()) {
                    System.out.println("보유 포인트보다 많이 사용할 수 없습니다.");
                    continue;
                }

                membershipService.addPoint(phone, -usePoint); // 포인트 차감
                System.out.printf("%d포인트를 사용했습니다.\n", usePoint);
            }


            int pointToAdd = 5000-usePoint;// 10% 적립
            System.out.printf("%d원 만큼 차감하여 %d원 결제하였습니다.\n",usePoint,pointToAdd);

            membershipService.addPoint(phone, pointToAdd);
            pointToAdd = pointToAdd/10;
            System.out.printf("%d포인트가 적립되었습니다. 현재 포인트: %d점\n",
                    pointToAdd,
                    membershipService.getMemberInfo(phone).getPoint());
        }
    }

    public static void main(String[] args) {
        Connection conn = JDBCUtil.getConnection(); // DB 연결 가져오기

        MembershipDao membershipDao = new MembershipDaoImpl(conn);
        PaymentDao paymentDao = new PaymentDaoImpl(conn);
        MembershipService membershipService = new MembershipServiceImpl(membershipDao);
        PaymentService paymentService = new PaymentServiceImpl(paymentDao);

        KioskApp app = new KioskApp(membershipService,paymentService);
        app.run();
        System.out.println(JDBCUtil.class.getResource("/application.properties"));
    }
}