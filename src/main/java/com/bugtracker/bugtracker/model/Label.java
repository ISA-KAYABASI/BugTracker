package com.bugtracker.bugtracker.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="department")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Label cannot be empty")
    @Column(name ="labelName" )
    private String labelName;



}
