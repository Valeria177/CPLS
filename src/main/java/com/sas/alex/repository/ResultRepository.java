package com.sas.alex.repository;

import com.sas.alex.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findByDescription(String description);
}
