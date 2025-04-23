package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.entity.ToDo;
import java.util.List;

public interface TodoRepository extends JpaRepository<ToDo, Long> {
    // select 문을 대신할 메소드 생성
    // 완료 목록 추출
    
    List<ToDo> findByCompleted(boolean completed);

    List<ToDo> findByImportanted(boolean importanted);

}
