package com.example.demo.repository;

import com.example.demo.model.Introduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroductionRepository extends JpaRepository<Introduction, Long> {
    Introduction findFirstByOrderByIdDesc();
}
