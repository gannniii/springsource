package com.example.book.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class BookDTO {

    private Long code;

    private String title;

    private String author;

    private int price;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
