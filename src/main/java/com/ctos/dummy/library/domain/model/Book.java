package com.ctos.dummy.library.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false)
    private String bookName;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Aisle> aisles = new HashSet<>();
}
