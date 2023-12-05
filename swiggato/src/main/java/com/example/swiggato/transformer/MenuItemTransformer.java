package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.MenuRequest;
import com.example.swiggato.dto.response.MenuResponse;
import com.example.swiggato.model.MenuItem;

public class MenuItemTransformer {
    public static MenuResponse MenuItemToMenuResponse(MenuItem menuItem){
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .foodCategory(menuItem.getFoodCategory())
                .price(menuItem.getPrice())
                .veg(menuItem.isVeg())
                .build();
    }
    public static MenuItem MenuRequestToMenuItem(MenuRequest menuRequest){
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .foodCategory(menuRequest.getFoodCategory())
                .build();
    }
}
