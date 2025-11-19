package com.ctos.dummy.library.domain.port.output;

import com.ctos.dummy.library.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    // Using Named Query defined in the entity
    @Query(name = "Library.findByLibraryNameContaining")
    List<Library> findByLibraryNameContaining(@Param("name") String name);

    Optional<Library> findByLibraryName(String libraryName);
}
