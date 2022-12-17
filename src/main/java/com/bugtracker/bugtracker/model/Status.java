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
@Table(name="status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "priority cannot be empty")
    @Column(name ="status" )
    private String status;



}
