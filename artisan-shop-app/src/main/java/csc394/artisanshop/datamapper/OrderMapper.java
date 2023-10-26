package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.OrderDto;
import csc394.artisanshop.entities.Order;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setUser(UserMapper.toUserDto(order.getUser()));
        orderDto.setItems(order.getProducts().stream()
                .map(ProductMapper::toProductDto)
                .collect(Collectors.toList()));


        return orderDto;
    }

    public static Order toOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setUser(UserMapper.toUser(orderDto.getUser()));
        order.setProducts(orderDto.getItems().stream()
                .map(ProductMapper::toProduct)
                .collect(Collectors.toList()));
        return order;
    }
}