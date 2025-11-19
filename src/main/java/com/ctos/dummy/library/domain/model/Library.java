package com.ctos.dummy.library.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(
        name = "Library.findByLibraryNameContaining",
        query = "SELECT l FROM Library l WHERE l.libraryName LIKE :name"
)
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryId;

    @Column(nullable = false)
    private String libraryName;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Aisle> aisles = new HashSet<>();

    public void addAisle(Aisle aisle) {
        aisles.add(aisle);
        aisle.setLibrary(this);
    }

    public void removeAisle(Aisle aisle) {
        aisles.remove(aisle);
        aisle.setLibrary(null);
    }
}
