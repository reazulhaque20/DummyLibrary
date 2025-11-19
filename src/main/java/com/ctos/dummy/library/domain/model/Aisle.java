package com.ctos.dummy.library.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aisles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aisle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aisleId;

    @Column(nullable = false)
    private String aisleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "aisle_books",
            joinColumns = @JoinColumn(name = "aisle_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @Builder.Default
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
        book.getAisles().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getAisles().remove(this);
    }
}