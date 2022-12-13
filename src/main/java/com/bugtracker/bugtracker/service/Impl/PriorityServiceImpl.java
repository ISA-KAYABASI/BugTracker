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

    @Override
    public Priority savePriority(Priority priority) throws ArithmeticException  {


        if (priorityRepository.existsByPriority(priority.getPriority())){
            throw new ArithmeticException("Same priority already exists: " + priority.getPriority());
        }else
        {
            return priorityRepository.save(priority);
        }
    }

    @Override
    public Priority updatePriority(Priority priority) {
        if (priorityRepository.existsByPriority(priority.getPriority())){
            throw new ArithmeticException("Same label already exists: " + priority.getPriority());
        }else
        {
            return priorityRepository.save(priority);
        }
    }

    @Override
    public Priority getPriorityById(long id) {
        Optional<Priority> optional = priorityRepository.findById(id);
        Priority priority = null;
        if(optional.isPresent()){
            priority = optional.get();
        }else {
            throw new RuntimeException("Priority not found for id :: " + id);
        }
        return priority;
    }
    @Override
    public void deletePriorityById(long id) {
        this.priorityRepository.deleteById(id);
    }


}
