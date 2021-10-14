package com.example.orderservice.service;

import com.example.orderservice.dto.Product;
import com.example.orderservice.dto.ResponseOrderWithProductDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseOrderWithProductDTO getOrderWithProduct(long userId,long productId) {

        ResponseOrderWithProductDTO responseOrderWithProductDTO = new ResponseOrderWithProductDTO();
        Order order = orderRepository.findById(productId).get();
        if(order.getUserId() == userId){
            Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+productId ,Product.class);
            responseOrderWithProductDTO.setOrder(order);
            responseOrderWithProductDTO.setProduct(product);
        }



        return responseOrderWithProductDTO;
    }

    @Override
    public List<Order> getOrderByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
