package org.software.controller.ordermanagement;

import org.software.model.PaymentManager;
import org.software.model.order.Order;
import org.software.utils.OrderDetailsMapper;
import org.software.view.ordermanagement.OrderManagementPanel;
import org.software.view.ordermanagement.PaymentFrame;

import java.math.BigDecimal;

public class PaymentController {
    private final PaymentFrame paymentFrame;
    private final PaymentManager paymentManager;
    private final Order order;

    public PaymentController(Order order) {
        this.paymentFrame = new PaymentFrame();
        this.paymentManager = PaymentManager.getInstance();
        this.order = order;
    }

    public void initialize() {
        configurePaymentFrame();
        registerActionListener();
        loadOrderDetails();
    }

    private void configurePaymentFrame() {
        paymentFrame.setSize(800, 400);
        paymentFrame.setLocationRelativeTo(null);
        paymentFrame.setResizable(false);
        paymentFrame.setVisible(true);
    }

    private void registerActionListener() {
        paymentFrame.addCheckoutButtonActionListener(e -> checkoutOrder());
    }

    private void loadOrderDetails() {
        String[][] data = OrderDetailsMapper.mapOrderDetails(order);
        paymentFrame.updateOrderDetailsTable(new String[]{"Name", "Price", "Quantity", "SubPrice"}, data);
        BigDecimal totalPrice = paymentManager.getTotalPrice(order);
        paymentFrame.updateOrderTotalPriceLabel(totalPrice);
    }

    private void checkoutOrder() {

    }
}
