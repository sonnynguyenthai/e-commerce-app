package com.sonny.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineReq orderLineReq) {
        return orderLineRepository.save(orderLineMapper.mapToOrder(orderLineReq)).getId();
    }

    public List<OrderLineResDTO> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::fromOrder)
                .collect(Collectors.toList())
                ;
    }}
