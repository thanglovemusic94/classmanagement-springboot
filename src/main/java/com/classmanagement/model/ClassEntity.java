package com.classmanagement.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name="majors_id", nullable=false)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Majors majors;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;

}
