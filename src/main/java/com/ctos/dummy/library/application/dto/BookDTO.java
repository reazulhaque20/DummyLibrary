package com.ctos.dummy.library.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Integer bookId;
    private String bookName;
}
