package com.ctos.dummy.library.domain.port.output;

import com.ctos.dummy.library.domain.model.Aisle;
import com.ctos.dummy.library.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Find list of books based on aisle
    @Query("SELECT b FROM Book b JOIN b.aisles a WHERE a = :aisle")
    List<Book> findByAisle(@Param("aisle") Aisle aisle);

    @Query("SELECT b FROM Book b JOIN b.aisles a WHERE a.aisleId = :aisleId")
    List<Book> findByAisleId(@Param("aisleId") Integer aisleId);
}
