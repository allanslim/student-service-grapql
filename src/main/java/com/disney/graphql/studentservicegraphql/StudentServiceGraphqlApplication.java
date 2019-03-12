package com.disney.graphql.studentservicegraphql;

import com.disney.graphql.studentservicegraphql.model.Student;
import com.disney.graphql.studentservicegraphql.service.StudentService;
import javafx.util.Pair;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class StudentServiceGraphqlApplication {

	@Bean
	ApplicationRunner init(StudentService studentService) {
		return args -> {
			Stream.of( new Pair<String,String>("David", "Shin"), new Pair("Larry", "Barker"), new Pair("Young Suk", "Park")).forEach( pair -> {
				Student student = new Student(UUID.randomUUID().toString(), pair.getKey().toString(), pair.getValue().toString());
				studentService.saveStudent(student);
			});
			studentService.findStudents().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceGraphqlApplication.class, args);
	}

}
