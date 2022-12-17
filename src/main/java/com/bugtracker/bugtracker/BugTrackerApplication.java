package com.bugtracker.bugtracker;

import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.model.Priority;
import com.bugtracker.bugtracker.model.Status;
import com.bugtracker.bugtracker.repository.LabelRepository;
import com.bugtracker.bugtracker.repository.PriorityRepository;
import com.bugtracker.bugtracker.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugTrackerApplication implements CommandLineRunner {

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private LabelRepository labelRepository;

    public static void main(String[] args) {
        SpringApplication.run(BugTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Created 5 rate priority for selection  

        Priority priority1 = priorityRepository.save(new Priority(1,"★"));
        Priority priority2 = priorityRepository.save(new Priority(2,"★★"));
        Priority priority3 = priorityRepository.save(new Priority(3,"★★★"));
        Priority priority4 = priorityRepository.save(new Priority(4,"★★★★"));
        Priority priority5 = priorityRepository.save(new Priority(5,"★★★★★"));

        Label label1 = labelRepository.save(new Label(1,"Backend"));
        Label label2 = labelRepository.save(new Label(2,"Frontend"));
        Label label3 = labelRepository.save(new Label(3,"Platfom"));
        Label label4 = labelRepository.save(new Label(4,"Mobile"));

        Status status1 = statusRepository.save(new Status(1,"Open"));
        Status status3 = statusRepository.save(new Status(2,"In Progress"));
        Status status4 = statusRepository.save(new Status(3,"Waiting for test process"));
        Status status5 = statusRepository.save(new Status(4,"Tested"));
        Status status2 = statusRepository.save(new Status(5,"Solved"));
        Status status6 = statusRepository.save(new Status(6,"Deployed"));



    }

}
