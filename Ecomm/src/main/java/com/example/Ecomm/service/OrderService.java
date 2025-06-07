package com.example.Ecomm.service;

import com.example.Ecomm.dto.OrderDTO;
import com.example.Ecomm.dto.OrderItemDTO;
import com.example.Ecomm.model.OrderItem;
import com.example.Ecomm.model.Orders;
import com.example.Ecomm.model.Product;
import com.example.Ecomm.model.User;
import com.example.Ecomm.repo.OrderRepo;
import com.example.Ecomm.repo.ProductRepo;
import com.example.Ecomm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not FOund"));

        Orders order=new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems=new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();

        for (Map.Entry<Long,Integer>entry:productQuantities.entrySet())
        {
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(()-> new RuntimeException("Product Not Found"));

            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDTOS.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));
        }

        order.setOrderItems(orderItems);
        Orders saveOrder = orderRepo.save(order);

        return new OrderDTO(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getStatus()
                ,saveOrder.getOrderDate(),orderItemDTOS);
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepo.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {
        List<OrderItemDTO> orderItem = orders.getOrderItems().stream()
                .map(item->new OrderItemDTO(
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity())).collect(Collectors.toList());

        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser()!=null ? orders.getUser().getName() : "Unknown",
                orders.getUser()!=null ? orders.getUser().getEmail() : "Unknown",
                orderItem
        );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {
        Optional<User> userOp = userRepo.findById(userId);
        if (userOp.isEmpty())
        {
            throw new RuntimeException("User Not Found");
        }
        User user=userOp.get();
        List<Orders> ordersList = orderRepo.findByUser(user);

        return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());

    }
}
