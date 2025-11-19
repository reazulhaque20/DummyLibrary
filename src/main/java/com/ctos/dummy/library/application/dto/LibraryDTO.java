package com.ctos.dummy.library.application.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDTO {
    private Integer libraryId;
    private String libraryName;
    private Set<AisleDTO> aisles;
}
