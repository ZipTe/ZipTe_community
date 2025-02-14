package com.zipte.platform.application.port.in.board;

import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.board.Category;

import java.util.List;

public interface GetCategoryInfoUseVase {

    // 전체 카테고리 보기
    List<Category> getAllInfo();

    // 카테고리 아이디로 조회
    Board getByCategoryId(Long categoryId);

}
