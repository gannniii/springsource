package com.example.jpa.repository;

import java.security.PublicKey;
import java.sql.Struct;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;

import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest // test용 클래스
public class StudentRepositoryTest {

    @Autowired // = new StudentRepository()
    private StudentRepository studentRepository;

    // CRUD test
    // Repository, Entity 확인
    // C(insert) : save(Entity)

    @Test
    public void insertTest() {

        // Entity 생성

        LongStream.range(1, 11).forEach(i -> {

            Student student = Student.builder()
                    .name("홍길동" + i)
                    .grade(Grade.JUNIOR)
                    .gender("M")
                    .build();

            // insert
            studentRepository.save(student);
        });

    }

    @Test // 테스트 메소드(테스트 메소드는 리턴 타입 void여야함)
    public void updateTest() {

        Student student = studentRepository.findById(1L).get();
        student.setGrade(Grade.SENIOR);
        // update
        studentRepository.save(student);
    }

    @Test
    public void selectOneTest() {
        // Optional<Student> student = studentRepository.findById(1L);

        // if (student.isPresent()) {
        // System.out.println(student.get());
        // }

        Student student = studentRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
        System.out.println(student);
    }

    @Test
    public void selectTest() {
        // List<Student> list = studentRepository.findAll();

        // for (Student student : list) {
        // System.out.println(student);
        // }

        studentRepository.findAll().forEach(student -> System.out.println(student));
    }

    @Test
    public void deleteTest() {

        // Student student = studentRepository.findById(11L).get();
        // studentRepository.delete(student);

        studentRepository.deleteById(10L);
    }
}
