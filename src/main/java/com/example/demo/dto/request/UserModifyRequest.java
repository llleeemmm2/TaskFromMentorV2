package com.example.demo.dto.request;

import java.time.Instant;

public record UserModifyRequest(String city,
                                Instant date,
                                String name) {
}
