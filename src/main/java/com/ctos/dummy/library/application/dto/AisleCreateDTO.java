package com.ctos.dummy.library.application.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AisleCreateDTO {
    private String aisleName;
    private Set<Integer> bookIds;
}
