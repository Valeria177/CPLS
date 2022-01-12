package com.sas.alex.repository;

import com.sas.alex.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    //Хз работает ли, взято с оверФЛОУ
    @Query("select a from Attempt a where a.finished=false and  a.user.id=:id")
    Attempt getCurrentAttempt(@Param("id") long id);

    @Query("select a from Attempt a where a.user.id=:id")
    List<Attempt> getAllUserAttempt(@Param("id") long id);

}
