package com.freelancing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancing.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
