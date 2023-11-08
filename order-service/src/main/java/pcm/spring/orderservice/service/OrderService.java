package pcm.spring.orderservice.service;

import pcm.spring.orderservice.dto.OrderDto;
import pcm.spring.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrderByUserId(String userId);
}
