package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.model.Bug;
import com.bugtracker.bugtracker.model.Label;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface BugService extends UserDetailsService {
    List<Bug> getAllBug();
    void saveBug(Bug bug);
    void updateBug(Bug bug);
    Bug getBugById(long id);
    void deleteBugById(long id);

}
