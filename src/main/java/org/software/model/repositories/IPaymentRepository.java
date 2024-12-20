package org.software.model.repositories;

import org.software.model.PaymentBill;

import java.util.List;

public interface IPaymentRepository extends Repository<PaymentBill, Integer> {
    List<PaymentBill> getAll();

    PaymentBill getById(Integer id);

    PaymentBill create(PaymentBill paymentBill);

    PaymentBill update(PaymentBill paymentBill);

    PaymentBill delete(PaymentBill paymentBill);
}
