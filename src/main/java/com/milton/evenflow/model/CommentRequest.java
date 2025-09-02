package com.milton.evenflow.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CommentRequest {
    @Positive(message = "id must be a positive number")
    private Integer id;

    @Min(value = 0, message = "delay must be at least 1%")
    @Max(value = 10, message = "delay cannot exceed 5%")
    private Long delay;
}
