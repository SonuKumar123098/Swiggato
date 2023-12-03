package com.example.swiggato.repository;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {


    public List<FoodItem> findAlByFoodCategory(FoodCategory foodCategory);
}
