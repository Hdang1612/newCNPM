package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Staff;

public interface StaffResponsitory extends JpaRepository<Staff, Integer> {
    
}
