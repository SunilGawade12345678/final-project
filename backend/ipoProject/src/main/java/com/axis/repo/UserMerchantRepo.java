package com.axis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.model.User;
import com.axis.model.UserMerchant;

public interface UserMerchantRepo extends JpaRepository<UserMerchant, Integer> {
  public UserMerchant findById(int id);
}
