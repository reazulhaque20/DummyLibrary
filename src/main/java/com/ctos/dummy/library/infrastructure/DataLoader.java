package com.ctos.dummy.library.infrastructure;

import com.ctos.dummy.library.domain.model.Aisle;
import com.ctos.dummy.library.domain.model.Book;
import com.ctos.dummy.library.domain.model.Library;
import com.ctos.dummy.library.domain.port.output.AisleRepository;
import com.ctos.dummy.library.domain.port.output.BookRepository;
import com.ctos.dummy.library.domain.port.output.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final LibraryRepository libraryRepository;
    private final AisleRepository aisleRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        // Create and save Books first
        Book book1 = bookRepository.save(Book.builder().bookName("The Origin of Species").build());
        Book book2 = bookRepository.save(Book.builder().bookName("A Brief History of Time").build());
        Book book3 = bookRepository.save(Book.builder().bookName("Cosmos").build());
        Book book4 = bookRepository.save(Book.builder().bookName("The Selfish Gene").build());
        Book book5 = bookRepository.save(Book.builder().bookName("Sapiens").build());
        Book book6 = bookRepository.save(Book.builder().bookName("The Greatest Show on Earth").build());

        // Create CENTRAL LIBRARY
        Library centralLibrary = Library.builder()
                .libraryName("CENTRAL LIBRARY")
                .aisles(new HashSet<>())
                .build();

        // Create Aisles for Central Library
        Aisle naturalHistoryAisle = Aisle.builder()
                .aisleName("NATURAL HISTORY")
                .books(new HashSet<>())
                .build();

        Aisle scienceAisle = Aisle.builder()
                .aisleName("SCIENCE")
                .books(new HashSet<>())
                .build();

        // Add books to Natural History aisle
        naturalHistoryAisle.getBooks().add(book1);
        naturalHistoryAisle.getBooks().add(book4);
        naturalHistoryAisle.getBooks().add(book5);
        naturalHistoryAisle.getBooks().add(book6);

        // Add books to Science aisle
        scienceAisle.getBooks().add(book2);
        scienceAisle.getBooks().add(book3);
        scienceAisle.getBooks().add(book4); // Book can be in multiple aisles

        // Add aisles to Central Library
        centralLibrary.addAisle(naturalHistoryAisle);
        centralLibrary.addAisle(scienceAisle);

        // Save Central Library (cascades to aisles)
        libraryRepository.save(centralLibrary);

        // Create PUBLIC LIBRARY
        Library publicLibrary = Library.builder()
                .libraryName("PUBLIC LIBRARY")
                .aisles(new HashSet<>())
                .build();

        Aisle historyAisle = Aisle.builder()
                .aisleName("HISTORY")
                .books(new HashSet<>())
                .build();

        // Add book to History aisle
        historyAisle.getBooks().add(book5);

        // Add aisle to Public Library
        publicLibrary.addAisle(historyAisle);

        // Save Public Library (cascades to aisles)
        libraryRepository.save(publicLibrary);

        System.out.println("Sample data loaded successfully!");
        System.out.println("H2 Console available at: http://localhost:8181/h2-console");
        System.out.println("Swagger UI available at: http://localhost:8181/swagger-ui.html");
    }
}
