package com.ctos.dummy.library.application.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AisleDTO {
    private Integer aisleId;
    private String aisleName;
    private Set<BookDTO> books;
}
