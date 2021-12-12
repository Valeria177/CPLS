package com.sas.alex.repository;

import com.sas.alex.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttemptRepository extends JpaRepository<Attempt,Long > {

    //Хз работает ли, взято с оверФЛОУ
    @Query(value = "select a from Attempt a where a.finished=false and a.user.id:id")
    Attempt getCurAttempt(@Param("id") Long id);

}
