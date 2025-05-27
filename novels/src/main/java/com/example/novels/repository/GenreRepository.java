package com.example.novels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.novels.entity.Genre;
import com.example.novels.entity.Novel;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
