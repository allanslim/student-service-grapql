package com.disney.graphql.studentservicegraphql.repository;

import com.disney.graphql.studentservicegraphql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
