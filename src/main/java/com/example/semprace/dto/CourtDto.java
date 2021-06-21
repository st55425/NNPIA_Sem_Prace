package com.example.semprace.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourtDto {

    private long Id;

    private String name;

    private boolean available;

    private long reservableTypeId;
}
