package com.example.jpa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemoDTO {
    private Long mno;
    private String memoText;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
