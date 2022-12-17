package com.bugtracker.bugtracker.service.Impl;

import com.bugtracker.bugtracker.model.Status;
import com.bugtracker.bugtracker.repository.StatusRepository;
import com.bugtracker.bugtracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getallStatus() {
        return statusRepository.findAll();
    }


}
