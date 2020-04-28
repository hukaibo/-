package com.example.demo.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;
    private String description;
    private Long userId;
    private Integer status;//0 -> disable(无用的); 1 -> enable(有用的)
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
