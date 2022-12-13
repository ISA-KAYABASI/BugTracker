package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.model.Priority;
import java.util.List;

public interface PriorityService {

    List<Priority> getAllPriority();

    Priority savePriority(Priority priority);

    Priority updatePriority(Priority priority);

    Priority getPriorityById(long id);

    void deletePriorityById(long id);
}
