package com.example.repository;

import com.example.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<customer,Long> {
    //查询方法

}
