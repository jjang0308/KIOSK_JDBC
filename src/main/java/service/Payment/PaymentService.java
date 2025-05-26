package service.Payment;

import domain.Payment.PaymentVO;
import java.util.List;

public interface PaymentService {
    List<String> getAllPaymentTypes();            // 결제 방식 리스트 조회
}
