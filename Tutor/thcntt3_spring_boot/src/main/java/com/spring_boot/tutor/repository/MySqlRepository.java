package com.spring_boot.tutor.repository;

import com.spring_boot.tutor.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRepository extends JpaRepository<Farm, Integer> {
}
