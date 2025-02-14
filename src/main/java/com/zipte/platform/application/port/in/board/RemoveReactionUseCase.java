package com.zipte.platform.application.port.in.board;

import com.zipte.platform.domain.board.UserReaction;

public interface RemoveReactionUseCase {

    // 감정 표현 지우기
    void removeReaction(Long boardId, Long memberId, UserReaction reaction);


}
