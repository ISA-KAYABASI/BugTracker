package com.bugtracker.bugtracker.repository;

import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PriorityRepository extends JpaRepository<Priority,Long> {

    boolean existsByPriority(String priority);

    Priority findByPriority(String priority);
}
