package com.bugtracker.bugtracker.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="bugs")
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2,max = 15,message = "The bug title must be between 2 and 15 characters")
    @Column(name ="title" )
    @Pattern(regexp = "^[a-zA-Z\s]+$", message = "Title must be letter with no special characters")
    private String title;

    @Size(min = 2,max = 300,message = "The employee last name must be between 2 and 300 characters")
    @Column(name ="description" )
    private String description;


    @Column(name = "priorities")
    private String priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Future
    private Date deadLine;


    @Column(name = "active ")
    private boolean isActive  = true;

    @Column(name ="labelName" )
    private String labelName;


    public Date getBirthday(Date currentDate) {
        return currentDate;
    }
}
