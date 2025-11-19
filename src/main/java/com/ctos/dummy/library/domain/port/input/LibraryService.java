package com.ctos.dummy.library.domain.port.input;

import com.ctos.dummy.library.application.dto.*;

import java.util.List;

public interface LibraryService {
    /**
     * Get all aisles based on library
     */
    List<AisleDTO> getAllAislesByLibrary(Integer libraryId);

    /**
     * Save library information
     */
    LibraryDTO saveLibrary(LibraryCreateDTO libraryCreateDTO);

    /**
     * Update library information
     */
    LibraryDTO updateLibrary(LibraryUpdateDTO libraryUpdateDTO);

    /**
     * Get all books based on aisle information
     */
    List<BookDTO> getAllBooksByAisle(Integer aisleId);

    /**
     * Get all books based on aisle name in a specific library
     */
    List<BookDTO> getAllBooksByAisleNameAndLibraryName(String aisleName, String libraryName);

    /**
     * Get all libraries
     */
    List<LibraryDTO> getAllLibraries();

    /**
     * Get library by ID
     */
    LibraryDTO getLibraryById(Integer libraryId);
}
