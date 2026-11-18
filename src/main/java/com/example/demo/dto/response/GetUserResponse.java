package com.example.demo.dto.response;

import java.time.Instant;
import java.time.LocalDate;

public record GetUserResponse(
        String city,
        Instant date,
        String name) {

}
