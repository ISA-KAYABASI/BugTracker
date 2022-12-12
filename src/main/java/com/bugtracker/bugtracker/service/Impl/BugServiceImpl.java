package com.bugtracker.bugtracker.service.Impl;

import com.bugtracker.bugtracker.model.Actor;
import com.bugtracker.bugtracker.model.Bug;
import com.bugtracker.bugtracker.model.Role;
import com.bugtracker.bugtracker.repository.ActorRepository;
import com.bugtracker.bugtracker.repository.LabelRepository;
import com.bugtracker.bugtracker.repository.BugRepository;
import com.bugtracker.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Bug> getAllBug() {
        return bugRepository.findAll();
    }


    @Override
    public void saveBug(Bug bug){

        //First name first letter to uppercase rest is lowercase
        int titlelength = bug.getTitle().length();
        bug.setTitle(bug.getTitle().substring(0,1).toUpperCase()+(bug.getTitle().substring(1,titlelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int descriptionLength = bug.getDescription().length();
        bug.setDescription(bug.getDescription().substring(0,1).toUpperCase()+(bug.getDescription().substring(1,descriptionLength).toLowerCase()));


//        Label newDep = departmentRepository.save(bug.getLabelName())
        this.bugRepository.save(bug);
    }

    @Override
    public void updateBug(Bug bug) {
        //First name first letter to uppercase rest is lowercase
        int titlelength = bug.getTitle().length();
        bug.setTitle(bug.getTitle().substring(0,1).toUpperCase()+(bug.getTitle().substring(1,titlelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int descriptionLength = bug.getDescription().length();
        bug.setDescription(bug.getDescription().substring(0,1).toUpperCase()+(bug.getDescription().substring(1,descriptionLength).toLowerCase()));





        this.bugRepository.save(bug);
    }

    @Override
    public Bug getBugById(long id) {


        Optional<Bug> optional = bugRepository.findById(id);
        Bug bug = null;
        if(optional.isPresent()){
            bug = optional.get();
        }else {
            throw new RuntimeException("Bug not found for id :: " + id);
        }
        return bug;
    }

    @Override
    public void deleteBugById(long id) {
        this.bugRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Actor actor = actorRepository.findByFirstName(username);

        if( actor == null ){
            throw new UsernameNotFoundException("Invalid username or password.");
        }else{
                return new org.springframework.security.core.userdetails.User(actor.getFirstName(),actor.getPassword(),mapRolesToAuthorities(actor.getActorRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles ){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
