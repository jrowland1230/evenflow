package com.milton.evenflow.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank(message = "Name must not be blank")
    private String postId;

    //@NotBlank(message = "Name must not be blank")
    @Min(value = 1, message = "Delay must be at least 1%")
    @Max(value = 5, message = "Delay cannot exceed 5%")
    private Long delay;
}
