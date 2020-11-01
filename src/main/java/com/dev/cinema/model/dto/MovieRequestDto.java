package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull(message = "Movie title should not be null")
    private String title;
    @NotNull(message = "Movie description should not be null")
    private String description;
}
