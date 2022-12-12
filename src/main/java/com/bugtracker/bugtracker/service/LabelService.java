package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.model.Label;

import java.util.List;


public interface LabelService {
    List<Label> getAllLabel();
    Label saveLabel(Label label);

    Label updateLabel(Label label);
    Label getLabelById(long id);



    void deleteLabelById(long id);
}
