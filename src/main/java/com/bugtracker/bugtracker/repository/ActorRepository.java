package com.hornsandspurs.hornsandspursmanagementsystem.repository;


import com.hornsandspurs.hornsandspursmanagementsystem.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long> {
    Actor findByFirstName (String firstName);



}
