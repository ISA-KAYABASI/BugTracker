package com.bugtracker.bugtracker.repository;

import com.bugtracker.bugtracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {

//    boolean existsByPriority(String priority);

}
