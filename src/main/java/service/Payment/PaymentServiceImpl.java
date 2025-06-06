package service.Payment;

package service.Payment;

import dao.Payment.PaymentDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> getAllPaymentTypes() {
        return paymentDao.getPayment_type();
    }

    // 사용자 입력 흐름 포함: 결제 수단 선택
    public String choosePaymentType() {
        List<String> paymentTypes = getAllPaymentTypes();

        System.out.println("\n[결제 수단 선택]");
        for (int i = 0; i < paymentTypes.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, paymentTypes.get(i));
        }

        int choice = 0;
        while (true) {
            System.out.print("결제 수단 번호를 선택하세요: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= paymentTypes.size()) {
                    break;
                } else {
                    System.out.println("유효한 번호를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }

        return paymentTypes.get(choice - 1);
    }
}

