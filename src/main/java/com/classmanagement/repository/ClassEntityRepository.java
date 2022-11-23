package com.classmanagement.repository;

import com.classmanagement.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassEntityRepository extends JpaRepository<ClassEntity, Integer> {
}
