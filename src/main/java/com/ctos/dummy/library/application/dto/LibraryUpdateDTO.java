package com.ctos.dummy.library.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryUpdateDTO {
    private Integer libraryId;
    private String libraryName;
}
