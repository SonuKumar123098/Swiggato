package com.example.swiggato.repository;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {


    public List<MenuItem> findAlByFoodCategory(FoodCategory foodCategory);
}
