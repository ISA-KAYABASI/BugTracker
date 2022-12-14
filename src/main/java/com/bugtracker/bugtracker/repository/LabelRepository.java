package com.bugtracker.bugtracker.repository;

import com.bugtracker.bugtracker.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

    boolean existsByLabelName(String labelName);

}
