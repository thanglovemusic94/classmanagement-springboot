package com.classmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Majors {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String code;
    private String name;

    @OneToMany(mappedBy="majors")
    private Set<ClassEntity> classEntities;
}
