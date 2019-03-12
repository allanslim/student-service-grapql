package com.disney.graphql.studentservicegraphql.service;

import com.disney.graphql.studentservicegraphql.model.Student;
import com.disney.graphql.studentservicegraphql.repository.StudentRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GraphQLQuery(description = "the id of the student")
    public Optional<Student> findStudentById(@GraphQLArgument(name = "id") String id) {
        return studentRepository.findById(id);
    }

    @GraphQLQuery(description = "the list of students")
    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    @GraphQLMutation(name = "saveStudent", description = "to save a student")
    public Student saveStudent(@GraphQLArgument(name = "saveStudent") Student student) {
        return studentRepository.save(student);
    }

    @GraphQLMutation(name = "deleteStudent", description = "to delete a student")
    public boolean deleteStudent(@GraphQLArgument(name = "studentIdToDelete") String id) {
        studentRepository.deleteById(id);
        return true;
    }
}
