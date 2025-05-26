package service.Payment;

import dao.Payment.PaymentDao;
import domain.Payment.PaymentVO;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<String> getAllPaymentTypes() {
        return paymentDao.getPayment_type();
    }
}
