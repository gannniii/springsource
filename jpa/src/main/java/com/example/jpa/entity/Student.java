package com.example.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "studenttbl")
@Entity // == db table
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq-gen", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq-gen")
    // @GeneratedValue
    private Long id; // id number(19, 0) not null primary key (id)

    private String name; // name varchar2(255 char)
}
