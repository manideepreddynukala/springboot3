package com.example.springboot3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    //The JpaRepository generic will take 2 arguments. The type of entity and the data type of the primary key of entity
}
