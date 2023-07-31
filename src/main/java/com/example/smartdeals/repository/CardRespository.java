package com.example.smartdeals.repository;

import com.example.smartdeals.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRespository extends JpaRepository<Card,Integer> {

    public Card findByCardNo(String cardNo);
}
