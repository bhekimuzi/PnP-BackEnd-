package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier,Long>{

}
