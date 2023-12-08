package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.exception.MenuItemNotFoundException;
import com.example.swiggato.exception.RestaurantNotFoundException;
import com.example.swiggato.model.*;
import com.example.swiggato.repository.CartRepository;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.FoodItemRepository;
import com.example.swiggato.repository.MenuItemRepository;
import com.example.swiggato.service.CartService;
import com.example.swiggato.service.FoodItemService;
import com.example.swiggato.transformer.CartTransformer;
import com.example.swiggato.transformer.FoodTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    final CustomerRepository customerRepository;
    final MenuItemRepository menuItemRepository;
    final CartRepository cartRepository;
    final FoodItemRepository foodItemRepository;

    @Autowired
    public CartServiceImpl(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, CartRepository cartRepository, FoodItemRepository foodItemRepository, FoodItemService foodItemService) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartRepository = cartRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Transactional
    @Override
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if (customer == null) {
            throw new CustomerNotFoundException("customer not found!");
        }
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(foodRequest.getMenuId());
        if (optionalMenuItem.isEmpty()) {
            throw new MenuItemNotFoundException("menu does not exist");
        }
        MenuItem menuItem = optionalMenuItem.get();
        if (!menuItem.isAvailable()) {
            throw new MenuItemNotFoundException("Item is not available in the restaurant ");
        }
        if (!menuItem.getRestaurant().isOpened()) {
            throw new RestaurantNotFoundException("Try later, Restaurant closed for now!");
        }
        //ready to add  item to cart
        Cart cart = customer.getCart();
//        FoodItem foodItem = FoodTransformer.FoodRequestToFoodItem(foodRequest, menuItem, customer);
        // not having item from the same restaurant
        if (cart.getFoodItems().size() != 0) {
            Restaurant currRestaurant = cart.getFoodItems().get(0).getMenu().getRestaurant();
            Restaurant newRestaurant = menuItem.getRestaurant();

            if (currRestaurant.getId() != newRestaurant.getId()) {
                List<FoodItem> foodItems = cart.getFoodItems();
                for(FoodItem foodItem1:foodItems){
                    foodItem1.setCart(null);
                    foodItem1.setOrderPlaced(null);
                    foodItem1.setMenu(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                //delete food items previously present in cart
                for (FoodItem foodItem1 : foodItems) {
                    foodItemRepository.deleteById(foodItem1.getId());
                }
            }
        }
        boolean exist = false;
        FoodItem savedFoodItem = null;
        //foodItem = foodItemRepository.save(foodItem);
        if (cart.getFoodItems().size() != 0) {
            for (FoodItem foodItem1 : cart.getFoodItems()) {
                if (foodItem1.getMenu().getId() == menuItem.getId()) {
                    savedFoodItem = foodItem1;
                    foodItem1.setRequiredQuantity(foodItem1.getRequiredQuantity() + foodRequest.getRequiredQuantity());
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            FoodItem foodItem = FoodTransformer.FoodRequestToFoodItem(foodRequest, menuItem);
            savedFoodItem = foodItemRepository.save(foodItem);
            cart.getFoodItems().add(savedFoodItem);
            menuItem.getFoodItems().add(savedFoodItem);
            savedFoodItem.setCart(cart);
        }
        cart.setCartTotal(cart.getCartTotal() + menuItem.getPrice() * foodRequest.getRequiredQuantity());
        Cart savedCart = cartRepository.save(cart);
//        menuItem.getFoodItems().add(foodItem);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        //prepare foodresponse list
        List<FoodResponse> foodResponseList = savedCart.getFoodItems().stream()
                .map(foodItem1 -> FoodTransformer.FoodItemToFoodResponse(foodItem1))
                .collect(Collectors.toList());
        CartStatusResponse cartStatusResponse = CartTransformer.CartToCartStatusResponse(savedCart, foodResponseList, savedMenuItem);
        return cartStatusResponse;
    }

    // having item from same restauran

//        foodItem.setCart(null);
//        foodItem.setOrder(null);
//        foodItem.setMenuItem(null);


//        double cartTotal=0;
//        for(FoodItem food:cart.getFoodItems()){
//        cartTotal+=food.getRequiredQuantity()*food.getMenuItem().getPrice();
//        }
//
//        cart.setCartTotal(cartTotal);
    // save
}