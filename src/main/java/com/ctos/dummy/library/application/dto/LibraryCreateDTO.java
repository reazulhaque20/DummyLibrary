package com.ctos.dummy.library.application.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryCreateDTO {
    private String libraryName;
    private Set<AisleCreateDTO> aisles;
}
