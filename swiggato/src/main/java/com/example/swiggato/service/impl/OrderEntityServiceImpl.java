package com.example.swiggato.service.impl;

import com.example.swiggato.dto.response.OrderResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.exception.EmptyCartException;
import com.example.swiggato.model.*;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.DeliveryPartnerRepository;
import com.example.swiggato.repository.OrderEntityRepository;
import com.example.swiggato.repository.RestaurantRepository;
import com.example.swiggato.service.OrderEntityService;
import com.example.swiggato.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    final CustomerRepository customerRepository;
    final DeliveryPartnerRepository deliveryPartnerRepository;
    final OrderEntityRepository orderEntityRepository;
    final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderEntityServiceImpl(CustomerRepository customerRepository, DeliveryPartnerRepository deliveryPartnerRepository, OrderEntityRepository orderEntityRepository, RestaurantRepository restaurantRepository) {
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public OrderResponse placeOrder(String customerMobile) {
//        //check that customer mobile is valid or not
        Customer customer=customerRepository.findByMobileNo(customerMobile);
        if(customer==null){
            throw new CustomerNotFoundException("check your mobile number that you have entered and try again");
        }
//        System.out.println(customer);
        Cart cart=customer.getCart();
        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Your cart is empty!");
        }
        //find deliveryPartner to deliver the order. Randomly
        DeliveryPartner deliveryPartner=deliveryPartnerRepository.findRandomDeliveryPartner();
        //prepare orderEntity
        OrderEntity orderEntity= OrderTransformer.prepareOrderEntity(customer,deliveryPartner);
        //place the order
        OrderEntity savedOrderEntity=orderEntityRepository.save(orderEntity);
        Restaurant restaurant=cart.getFoodItems().get(0).getMenu().getRestaurant();
        //set Parent
        customer.getOrderEntityList().add(savedOrderEntity);
        restaurant.getOrderEntityList().add(savedOrderEntity);
        deliveryPartner.getOrderEntityList().add(savedOrderEntity);
        // now food items is not a part of cart, update order and cart in food item
        for(FoodItem foodItem:cart.getFoodItems()){
            foodItem.setOrderPlaced(savedOrderEntity);
            foodItem.setCart(null);
        }
        //clear the cart
        clearCart(cart);
        // save parent Entity
        customerRepository.save(customer);
        restaurantRepository.save(restaurant);
        deliveryPartnerRepository.save(deliveryPartner);
//         prepare order response
        return OrderTransformer.OrderEntityToOrderResponse(savedOrderEntity);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.getFoodItems().clear();
    }
}
