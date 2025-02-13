package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {


}
