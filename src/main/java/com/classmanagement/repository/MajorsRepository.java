package com.classmanagement.repository;

import com.classmanagement.model.Majors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorsRepository extends JpaRepository<Majors, Integer> {
}
