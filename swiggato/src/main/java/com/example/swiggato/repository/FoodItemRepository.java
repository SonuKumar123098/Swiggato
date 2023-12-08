package com.example.swiggato.repository;

import com.example.swiggato.model.Cart;
import com.example.swiggato.model.FoodItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {
//    String CartIsNull="delete f from FoodItem f where f.cartId is null";
//    @Transactional
//    @Modifying
//    @Query(value =CartIsNull)
//    public void deleteFood();
}
