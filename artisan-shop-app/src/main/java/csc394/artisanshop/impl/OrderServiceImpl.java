package csc394.artisanshop.impl;

import csc394.artisanshop.datamapper.ItemMapper;
import csc394.artisanshop.datamapper.OrderMapper;
import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.dto.OrderDto;
import csc394.artisanshop.dto.SellerDto;
import csc394.artisanshop.dto.UserDto;
import csc394.artisanshop.entities.Item;
import csc394.artisanshop.entities.Order;
import csc394.artisanshop.repositories.ItemDtoRepository;
import csc394.artisanshop.repositories.OrderDtoRepository;
import csc394.artisanshop.repositories.SellerDtoRepository;
import csc394.artisanshop.repositories.UserDtoRepository;
import csc394.artisanshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserDtoRepository userRepository;
    private final ItemDtoRepository itemRepository;
    private final OrderDtoRepository orderRepository;
    private  final SellerDtoRepository sellerRepository;

    @Autowired
    public OrderServiceImpl(final UserDtoRepository userRepository,
                            final ItemDtoRepository itemRepository,
                            final OrderDtoRepository orderRepository,
                            final SellerDtoRepository sellerRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Order placeOrder(UUID userId, Long shopId, List<Item> items) {
        List<ItemDto> itemDtos = items.stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());

        SellerDto seller = sellerRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        UserDto user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(new Date());
        orderDto.setUser(user);
        orderDto.setSeller(seller);
        orderDto.setItems(itemDtos);

        for(ItemDto itemDto : itemDtos) {
            itemDto.setOrder(orderDto);
        }

        orderDto = orderRepository.save(orderDto);
        return OrderMapper.toOrder(orderDto);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        Optional<OrderDto> orderDtos = orderRepository.findById(userId);
        return orderDtos.stream().map(OrderMapper::toOrder).collect(Collectors.toList());
    }
}
