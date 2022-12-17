package com.bugtracker.bugtracker.service.Impl;

import com.bugtracker.bugtracker.model.Priority;
import com.bugtracker.bugtracker.repository.PriorityRepository;
import com.bugtracker.bugtracker.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public List<Priority> getAllPriority() {
        return priorityRepository.findAll();
    }


}
