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
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int code;
    private String name;
    private String capacity;

    @OneToMany(mappedBy="room")
    private Set<ClassEntity> classEntities;
}
