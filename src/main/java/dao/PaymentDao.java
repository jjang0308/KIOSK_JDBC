package dao;

import domain.PaymentVO;
import java.util.List;

public interface PaymentDao {
    void insert(PaymentVO Payment);
    int getTotalCount();
    List<String> getPayment_type(); // 결제 방식 얻기
}
