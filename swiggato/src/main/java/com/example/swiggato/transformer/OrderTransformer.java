package com.example.swiggato.transformer;

import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.dto.response.OrderResponse;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.Customer;
import com.example.swiggato.model.DeliveryPartner;
import com.example.swiggato.model.OrderEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderTransformer {
    public static OrderEntity prepareOrderEntity(Customer customer, DeliveryPartner deliveryPartner){
        Cart cart=customer.getCart();
        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .customer(customer)
                .deliveryPartner(deliveryPartner)
                .foodItemList(cart.getFoodItems())
                .restaurant(cart.getFoodItems().get(0).getMenu().getRestaurant())
                .build();
    }
    public static OrderResponse OrderEntityToOrderResponse(OrderEntity orderEntity){
        List<FoodResponse>foodResponses=orderEntity.getFoodItemList().stream()
                .map(foodItem -> FoodTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return OrderResponse.builder()
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .orderTime(orderEntity.getOrderTime())
                .customerName(orderEntity.getCustomer().getName())
                .customerMobile(orderEntity.getCustomer().getMobileNo())
                .customerAddress(orderEntity.getCustomer().getAddress())
                .deliveryPartnerMobile(orderEntity.getDeliveryPartner().getMobileNo())
                .deliveryPartnerName(orderEntity.getDeliveryPartner().getName())
                .restaurantName(orderEntity.getRestaurant().getName())
                .restaurantLocation(orderEntity.getRestaurant().getLocation())
                .restaurantMobile(orderEntity.getRestaurant().getContactNumber())
                .foodList(foodResponses)
                .build();
    }
}
