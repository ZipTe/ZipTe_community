package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    
}
