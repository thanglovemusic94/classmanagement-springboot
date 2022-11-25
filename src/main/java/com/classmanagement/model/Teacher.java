package com.classmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String age;

    @OneToMany(mappedBy="teacher")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ClassEntity> classEntities;

}
