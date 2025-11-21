package com.ctos.dummy.library.adapter.inout.rest;

import com.ctos.dummy.library.application.dto.*;
import com.ctos.dummy.library.domain.port.input.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
@Tag(name = "Library Management", description = "APIs for managing libraries, aisles, and books")
public class LibraryController {

    private static final Logger log = LogManager.getLogger(LibraryController.class);
    private final LibraryService libraryService;

    /**
     * GET all books based on aisle name in a specific library
     * Example: GET /api/libraries/books?libraryName=CENTRAL LIBRARY&aisleName=NATURAL HISTORY
     */
    @GetMapping("/books")
    @Operation(summary = "Get books by aisle name and library name")
    public ResponseEntity<List<BookDTO>> getBooksByAisleAndLibrary(
            @RequestParam String libraryName,
            @RequestParam String aisleName) {

        log.info("Param Received: {}, {}", libraryName, aisleName);
        List<BookDTO> books = libraryService.getAllBooksByAisleNameAndLibraryName(
                aisleName, libraryName);

        log.info("Books List: {}", books);
        return ResponseEntity.ok(books);
    }

    /**
     * POST - Save library information
     */
    @PostMapping
    @Operation(summary = "Create a new library")
    public ResponseEntity<LibraryDTO> saveLibrary(@RequestBody LibraryCreateDTO libraryCreateDTO) {
        LibraryDTO savedLibrary = libraryService.saveLibrary(libraryCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibrary);
    }

    /**
     * PUT - Update library information
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update library information")
    public ResponseEntity<LibraryDTO> updateLibrary(
            @PathVariable Integer id,
            @RequestBody LibraryUpdateDTO libraryUpdateDTO) {

        libraryUpdateDTO.setLibraryId(id);
        LibraryDTO updatedLibrary = libraryService.updateLibrary(libraryUpdateDTO);
        return ResponseEntity.ok(updatedLibrary);
    }

    /**
     * GET - Get all libraries
     */
    @GetMapping
    @Operation(summary = "Get all libraries")
    public ResponseEntity<List<LibraryDTO>> getAllLibraries() {
        List<LibraryDTO> libraries = libraryService.getAllLibraries();
        return ResponseEntity.ok(libraries);
    }

    /**
     * GET - Get library by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get library by ID")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Integer id) {
        LibraryDTO library = libraryService.getLibraryById(id);
        return ResponseEntity.ok(library);
    }

    /**
     * GET - Get all aisles by library ID
     */
    @GetMapping("/{id}/aisles")
    @Operation(summary = "Get all aisles in a library")
    public ResponseEntity<List<AisleDTO>> getAislesByLibrary(@PathVariable Integer id) {
        List<AisleDTO> aisles = libraryService.getAllAislesByLibrary(id);
        return ResponseEntity.ok(aisles);
    }

    /**
     * GET - Get all books by aisle ID
     */
    @GetMapping("/aisles/{aisleId}/books")
    @Operation(summary = "Get all books in an aisle")
    public ResponseEntity<List<BookDTO>> getBooksByAisle(@PathVariable Integer aisleId) {
        List<BookDTO> books = libraryService.getAllBooksByAisle(aisleId);
        return ResponseEntity.ok(books);
    }
}
