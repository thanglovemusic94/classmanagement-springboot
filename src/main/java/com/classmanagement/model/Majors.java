package com.classmanagement.model;

import lombok.*;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ClassEntity> classEntities;
}
