package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.exception.MenuItemNotFoundException;
import com.example.swiggato.exception.RestaurantNotFoundException;
import com.example.swiggato.model.*;
import com.example.swiggato.repository.CartRepository;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.MenuItemRepository;
import com.example.swiggato.service.CartService;
import com.example.swiggato.transformer.CartTransformer;
import com.example.swiggato.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    final CustomerRepository customerRepository;
    final MenuItemRepository menuItemRepository;
    final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartRepository = cartRepository;
    }

    //    final Men
    @Override
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer=customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer.equals(null)){
            throw new CustomerNotFoundException("customer not found!");
        }
        Optional<MenuItem>optionalMenuItem=menuItemRepository.findById(foodRequest.getMenuId());
        if(optionalMenuItem.isEmpty()){
            throw new MenuItemNotFoundException("menu does not exist");
        }
        MenuItem menuItem=optionalMenuItem.get();
        if(!menuItem.isAvailable()){
            throw new MenuItemNotFoundException("menu is out of stock for Now");
        }
        if(!menuItem.getRestaurant().isOpened()){
            throw new RestaurantNotFoundException("Try later, Restaurant closed for now!");
        }
        //ready to add  item to cart
        Cart cart=customer.getCart();
        FoodItem foodItem= FoodTransformer.FoodRequestToFoodItem(foodRequest,menuItem,customer);
        // having item from the same restaurant
        if(cart.getFoodItems().size()!=0){
            Restaurant currRestaurant=cart.getFoodItems().get(0).getMenu().getRestaurant();
            Restaurant newRestaurant=menuItem.getRestaurant();

            if(!currRestaurant.equals(newRestaurant)){
//                List<FoodItem>foodItems=cart.getFoodItems();
//                for(FoodItem foodItem:foodItems){
//                    foodItem.setCart(null);
//                    foodItem.setOrder();
//                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                cart.getFoodItems().add(foodItem);
            }else{
                cart.getFoodItems().add(foodItem);
            }
        }
        Cart savedCart=cartRepository.save(cart);
        CartStatusResponse cartStatusResponse= CartTransformer.CartToCartStatusResponse(savedCart);
        return cartStatusResponse;
    }
}
