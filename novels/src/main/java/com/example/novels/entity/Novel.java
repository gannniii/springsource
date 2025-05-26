package com.example.novels.entity;

import java.time.LocalDate;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Novel {
    private Long id;

    private String title;

    private String author;

    private LocalDate publishedDate;

    private boolean available;

    private int grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;
}
