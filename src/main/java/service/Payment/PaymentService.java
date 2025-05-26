package service.Payment;

import domain.Payment.PaymentVO;
import java.util.List;

public interface PaymentService {
    void registerPayment(PaymentVO payment);      // 결제 수단 등록
    PaymentVO getPaymentById(long id);            // 결제 수단 조회
    List<String> getAllPaymentTypes();            // 결제 방식 리스트 조회
    int getTotalPaymentCount();                   // 전체 결제 건수 조회
}
