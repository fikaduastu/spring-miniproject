package com.example.accountservice.service;

import com.example.accountservice.dto.*;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService2 {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public  User getUserById(long userId) {
        return userRepository.findById(userId).get();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseUserWithOrderDTO getUserWithOrder(long userId) {

        ResponseUserWithOrderDTO responseUserWithOrderDTO = new ResponseUserWithOrderDTO();
        User user = userRepository.findById(userId).get();
        System.out.println(userId);
        ResponseEntity<Order[]> order = restTemplate.getForEntity("http://ORDER-SERVICE/api/" + user.getId()+"/orders" ,Order[].class);
        Order[] orders = order.getBody();
        List<Order> orderList = Arrays.stream(orders).collect(Collectors.toList());

//        OrderList orderList = restTemplate.getForObject("http://ORDER-SERVICE/orders/" + user.getUserId() ,OrderList.class);
//        List<Order>orders = orderList.getOrders();

//        Product product = restTemplate.getForObject("http://ORDER-SERVICE/product/"+ order.getProductId() ,Product.class);

        responseUserWithOrderDTO.setUser(user);
        responseUserWithOrderDTO.setOrder(orderList);
//        responseUserWithOrderDTO.setProduct(product);

        return responseUserWithOrderDTO;
    }

    @Override
    public ResponseOrderWithProductDTO getOrderWithProduct(long userId, long orderId) {
        ResponseOrderWithProductDTO responseOrderWithProductDTO = new ResponseOrderWithProductDTO();

        Order order = restTemplate.getForObject("http://ORDER-SERVICE/api/" + orderId,Order.class);
        if(order.getUserId() == userId){
            Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/products/getProduct/"+ order.getProductId() ,Product.class);

            responseOrderWithProductDTO.setOrder(order);
            responseOrderWithProductDTO.setProduct(product);
        }
        return responseOrderWithProductDTO;
    }
}
