package com.bugtracker.bugtracker.service.Impl;



import com.bugtracker.bugtracker.model.Label;
import com.bugtracker.bugtracker.repository.LabelRepository;
import com.bugtracker.bugtracker.service.LabelService;
import com.bugtracker.bugtracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private BugRepository bugRepository;


    @Override
    public List<Label> getAllLabel() {
        return labelRepository.findAll();
    }

    @Override
    public Label saveLabel(Label label) throws ArithmeticException  {

        // Label name turn first letter uppercase
        int departmentNameLength = label.getLabelName().length();
        label.setLabelName(label.getLabelName().substring(0,1).toUpperCase()+(label.getLabelName().substring(1,departmentNameLength).toLowerCase()));

        if (labelRepository.existsByLabelName(label.getLabelName())){
            throw new ArithmeticException("Same label already exists: " + label.getLabelName());
        }else
        {
        return labelRepository.save(label);
        }
    }



    @Override
    public Label updateLabel(Label label) {
        if (labelRepository.existsByLabelName(label.getLabelName())){
            throw new ArithmeticException("Same label already exists: " + label.getLabelName());
        }else
        {
            return labelRepository.save(label);
        }
    }

    @Override
    public Label getLabelById(long id) {
        Optional<Label> optional = labelRepository.findById(id);
        Label label = null;
        if(optional.isPresent()){
            label = optional.get();
        }else {
            throw new RuntimeException("Label not found for id :: " + id);
        }
        return label;
    }
    @Override
    public void deleteLabelById(long id) {
        this.labelRepository.deleteById(id);
    }


}
