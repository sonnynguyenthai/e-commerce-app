package com.sonny.ecommerce.order;

import com.sonny.ecommerce.customer.CustomerClient;
import com.sonny.ecommerce.exception.BusinessException;
import com.sonny.ecommerce.kafka.OrderConfirmation;
import com.sonny.ecommerce.kafka.OrderProducer;
import com.sonny.ecommerce.orderline.OrderLineReq;
import com.sonny.ecommerce.orderline.OrderLineService;
import com.sonny.ecommerce.payment.PaymentReqDTO;
import com.sonny.ecommerce.paymnet.PaymentClient;
import com.sonny.ecommerce.paymnet.PaymentReq;
import com.sonny.ecommerce.product.ProductClient;
import com.sonny.ecommerce.product.PurchaseReq;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private  final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer create(OrderReqDTO req) {
        //check customer
        var customer = customerClient.findById(req.customerId())
                .orElseThrow(() -> new BusinessException("Cannot find customer with id " + req.customerId() + " when create order"));

        // purchase the products ----> call product microservice
        var purchaseProducts = this.productClient.purchaseProducts(req.products());


        //persist order
        var order = this.orderRepository.save(orderMapper.mapToOrder(req));
        //persist order lines
        for(PurchaseReq purchaseReq: req.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineReq(
                            null,
                            order.getId(),
                            purchaseReq.productId(),
                            purchaseReq.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentReq(
                req.amount(),
                req.paymentMethod(),
                req.id(),
                req.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        //start payment process
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        req.reference(),
                        req.amount(),
                        req.paymentMethod(),
                        customer,
                        purchaseProducts

                )
        );

        //send the order confirmation ---> notification microservice

        return order.getId();
    }

    public List<OrderResDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public  OrderResDTO findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));
    }
}
