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
import com.example.swiggato.transformer.CartTransformer;
import com.example.swiggato.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public CartServiceImpl(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, CartRepository cartRepository, FoodItemRepository foodItemRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartRepository = cartRepository;
        this.foodItemRepository = foodItemRepository;
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
        // not having item from the same restaurant
        if(cart.getFoodItems().size()!=0){
            Restaurant currRestaurant=cart.getFoodItems().get(0).getMenu().getRestaurant();
            Restaurant newRestaurant=menuItem.getRestaurant();

            if(currRestaurant.getId()!=newRestaurant.getId()){
//                List<FoodItem>foodItems=cart.getFoodItems();
//                for(FoodItem foodItem:foodItems){
//                    foodItem.setCart(null);
//                    foodItem.setOrder();
//                }
                cart.setCartTotal(0);
                // Old foof available in cart
                List<FoodItem>foods=new ArrayList<>(cart.getFoodItems());
                //delete food items previously present in cart
                for(FoodItem foodItem1:foods){
                    foodItemRepository.deleteById(foodItem1.getId());
                }
                cart.getFoodItems().clear();
                cart.getFoodItems().add(foodItem);
                cart.setCartTotal(menuItem.getPrice()*foodRequest.getRequiredQuantity());
            }else{//having item from the same restaurant
                //check items already available in the cart then just increse the number of required quantity
                boolean exist=false;
                for(FoodItem foodItem1: cart.getFoodItems()){
                    if(foodItem1.getMenu().getId()==foodRequest.getMenuId()){
                        exist=true;
                        foodItem1.setRequiredQuantity(foodItem1.getRequiredQuantity()+foodRequest.getRequiredQuantity());
                        break;
                    }
                }
                if(!exist)cart.getFoodItems().add(foodItem);
                cart.setCartTotal(cart.getCartTotal()+menuItem.getPrice()*foodRequest.getRequiredQuantity());
            }
        }
        Cart savedCart=cartRepository.save(cart);
        //prepare foodresponse list
        List<FoodResponse>foodResponseList=savedCart.getFoodItems().stream()
                .map(foodItem1 -> FoodTransformer.FoodItemToFoodResponse(foodItem1))
                .collect(Collectors.toList());
        CartStatusResponse cartStatusResponse= CartTransformer.CartToCartStatusResponse(savedCart,foodResponseList,menuItem);
        return cartStatusResponse;
    }
}
