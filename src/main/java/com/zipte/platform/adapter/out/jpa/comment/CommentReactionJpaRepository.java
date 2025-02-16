package com.zipte.platform.adapter.out.jpa.comment;

import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReactionJpaRepository extends JpaRepository<CommentReactionJpaEntity, Long> {

    Optional<CommentReactionJpaEntity> findByCommentIdAndMember(Long commentId, Member member);
    Optional<CommentReactionJpaEntity> findByCommentIdAndMemberAndReactionType(Long commentId, Member member, UserReaction reactionType);
}
