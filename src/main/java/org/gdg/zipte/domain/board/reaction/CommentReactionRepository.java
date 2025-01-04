package org.gdg.zipte.domain.board.reaction;

import org.gdg.zipte.domain.board.comment.Comment;
import org.gdg.zipte.domain.user.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    Optional<CommentReaction> findByCommentAndMember(Comment comment, Member member);
    Optional<CommentReaction> findByCommentAndMemberAndReactionType(Comment comment, Member member, UserReaction reactionType);
}
