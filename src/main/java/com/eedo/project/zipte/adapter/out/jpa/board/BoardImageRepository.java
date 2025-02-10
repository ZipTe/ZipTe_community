package com.eedo.project.zipte.adapter.out.jpa.board;

import com.eedo.project.zipte.domain.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    
}
