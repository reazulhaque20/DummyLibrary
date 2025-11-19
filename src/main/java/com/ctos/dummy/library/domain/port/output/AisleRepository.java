package com.ctos.dummy.library.domain.port.output;

import com.ctos.dummy.library.domain.model.Aisle;
import com.ctos.dummy.library.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AisleRepository extends JpaRepository<Aisle, Integer> {

    // Find list of aisles based on library
    List<Aisle> findByLibrary(Library library);

    @Query("SELECT a FROM Aisle a WHERE a.library.libraryId = :libraryId")
    List<Aisle> findByLibraryId(@Param("libraryId") Integer libraryId);

    @Query("SELECT a FROM Aisle a WHERE a.library.libraryName = :libraryName AND a.aisleName = :aisleName")
    Optional<Aisle> findByLibraryNameAndAisleName(
            @Param("libraryName") String libraryName,
            @Param("aisleName") String aisleName
    );
}
