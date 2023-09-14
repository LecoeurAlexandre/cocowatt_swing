package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CommentDTO {
    private String id;
    private int value;
    private String comment;
    private LocalDate date;
    private int userId;
}
