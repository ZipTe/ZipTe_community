package com.eedo.project.zipte.infrastructure.out.persistence.jpa.board;

import com.eedo.project.zipte.domain.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    
}
