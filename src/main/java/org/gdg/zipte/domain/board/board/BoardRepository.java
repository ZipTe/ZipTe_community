package org.gdg.zipte.domain.board.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select bi from BoardImage bi join Board b on bi.board.id = :boardId")
    List<BoardImage> selectBoardImage(@Param("boardId") Long boardId);

}
