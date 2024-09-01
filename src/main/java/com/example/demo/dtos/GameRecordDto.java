package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameRecordDto(@NotBlank String gameName, @NotNull Integer gameYear) {
}
