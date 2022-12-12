package com.bugtracker.bugtracker.repository;


import com.bugtracker.bugtracker.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long> {
    Actor findByFirstName (String firstName);



}
