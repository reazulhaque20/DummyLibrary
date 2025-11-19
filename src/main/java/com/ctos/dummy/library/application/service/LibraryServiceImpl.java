package com.ctos.dummy.library.application.service;

import com.ctos.dummy.library.application.dto.*;
import com.ctos.dummy.library.domain.model.Aisle;
import com.ctos.dummy.library.domain.model.Book;
import com.ctos.dummy.library.domain.model.Library;
import com.ctos.dummy.library.domain.port.input.LibraryService;
import com.ctos.dummy.library.domain.port.output.AisleRepository;
import com.ctos.dummy.library.domain.port.output.BookRepository;
import com.ctos.dummy.library.domain.port.output.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final AisleRepository aisleRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AisleDTO> getAllAislesByLibrary(Integer libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        return aisleRepository.findByLibrary(library).stream()
                .map(this::mapAisleToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryDTO saveLibrary(LibraryCreateDTO dto) {
        Library library = Library.builder()
                .libraryName(dto.getLibraryName())
                .aisles(new HashSet<>())
                .build();

        if (dto.getAisles() != null && !dto.getAisles().isEmpty()) {
            dto.getAisles().forEach(aisleDto -> {
                Aisle aisle = Aisle.builder()
                        .aisleName(aisleDto.getAisleName())
                        .books(new HashSet<>())
                        .build();

                if (aisleDto.getBookIds() != null && !aisleDto.getBookIds().isEmpty()) {
                    List<Book> books = bookRepository.findAllById(aisleDto.getBookIds());
                    books.forEach(aisle::addBook);
                }

                library.addAisle(aisle);
            });
        }

        Library savedLibrary = libraryRepository.save(library);
        return mapLibraryToDTO(savedLibrary);
    }

    @Override
    public LibraryDTO updateLibrary(LibraryUpdateDTO dto) {
        Library library = libraryRepository.findById(dto.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + dto.getLibraryId()));

        library.setLibraryName(dto.getLibraryName());
        Library updatedLibrary = libraryRepository.save(library);

        return mapLibraryToDTO(updatedLibrary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooksByAisle(Integer aisleId) {
        Aisle aisle = aisleRepository.findById(aisleId)
                .orElseThrow(() -> new RuntimeException("Aisle not found with id: " + aisleId));

        return bookRepository.findByAisle(aisle).stream()
                .map(this::mapBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooksByAisleNameAndLibraryName(String aisleName, String libraryName) {
        Aisle aisle = aisleRepository.findByLibraryNameAndAisleName(libraryName, aisleName)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Aisle '%s' not found in library '%s'", aisleName, libraryName)));

        return bookRepository.findByAisle(aisle).stream()
                .map(this::mapBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibraryDTO> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(this::mapLibraryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LibraryDTO getLibraryById(Integer libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        return mapLibraryToDTO(library);
    }

    // ============= Mapping Methods =============

    private LibraryDTO mapLibraryToDTO(Library library) {
        return LibraryDTO.builder()
                .libraryId(library.getLibraryId())
                .libraryName(library.getLibraryName())
                .aisles(library.getAisles().stream()
                        .map(this::mapAisleToDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private AisleDTO mapAisleToDTO(Aisle aisle) {
        return AisleDTO.builder()
                .aisleId(aisle.getAisleId())
                .aisleName(aisle.getAisleName())
                .books(aisle.getBooks().stream()
                        .map(this::mapBookToDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private BookDTO mapBookToDTO(Book book) {
        return BookDTO.builder()
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .build();
    }
}
