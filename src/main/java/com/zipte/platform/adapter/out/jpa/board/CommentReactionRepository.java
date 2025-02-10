package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.Comment;
import com.zipte.platform.domain.board.CommentReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    Optional<CommentReaction> findByCommentAndMember(Comment comment, Member member);
    Optional<CommentReaction> findByCommentAndMemberAndReactionType(Comment comment, Member member, UserReaction reactionType);
}
