package com.axis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.model.ValidateUserMerchantData;

public interface ValidateRepo extends JpaRepository<ValidateUserMerchantData, Integer>  {

}
