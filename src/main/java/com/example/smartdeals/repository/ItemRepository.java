package com.example.smartdeals.repository;

import com.example.smartdeals.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
