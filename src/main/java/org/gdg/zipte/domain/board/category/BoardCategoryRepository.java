package org.gdg.zipte.domain.board.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

    @Query("SELECT bc FROM BoardCategory bc WHERE bc.parent IS NULL")
    List<BoardCategory> findRootCategories();

}
