package service;

import dao.PaymentDao;
import domain.PaymentVO;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void registerPayment(PaymentVO payment) {
        paymentDao.insert(payment);
    }

    @Override
    public PaymentVO getPaymentById(long id) {
        // 구현이 필요한 경우 dao에 해당 메서드를 추가해야 함
        throw new UnsupportedOperationException("getPaymentById는 DAO에서 구현되지 않음");
    }

    @Override
    public List<String> getAllPaymentTypes() {
        return paymentDao.getPayment_type();
    }

    @Override
    public int getTotalPaymentCount() {
        return paymentDao.getTotalCount();
    }
}
