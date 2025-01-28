package com.eedo.project.zipte.infrastructure.out.persistence.jpa.board;

import com.eedo.project.zipte.domain.board.Comment;
import com.eedo.project.zipte.domain.board.CommentReaction;
import com.eedo.project.zipte.domain.board.UserReaction;
import com.eedo.project.zipte.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    Optional<CommentReaction> findByCommentAndMember(Comment comment, Member member);
    Optional<CommentReaction> findByCommentAndMemberAndReactionType(Comment comment, Member member, UserReaction reactionType);
}
