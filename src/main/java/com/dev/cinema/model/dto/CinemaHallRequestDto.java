package com.dev.cinema.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull(message = "Cinema hall capacity should not be null")
    @Min(value = 10, message = "Cinema hall capacity should be more than 10")
    private Integer capacity;
    @NotNull(message = "Cinema hall description should not be null")
    private String description;
}
