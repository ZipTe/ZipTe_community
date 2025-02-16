package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.comment.CommentReactionJpaEntity;
import com.zipte.platform.adapter.out.jpa.comment.CommentReactionJpaRepository;
import com.zipte.platform.application.port.out.comment.CommentReactionPort;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.comment.CommentReaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentReactionPersistenceAdapter implements CommentReactionPort {

    private final CommentReactionJpaRepository repository;

    @Override
    public CommentReaction saveBoardReaction(CommentReaction reaction) {
        var commentReaction = CommentReactionJpaEntity.from(reaction);

        return repository.save(commentReaction)
                .toDomain();
    }

    @Override
    public Optional<CommentReaction> loadBoardReaction(Long commentId, Long memberId) {

        return repository.findByCommentIdAndMemberId(commentId, memberId)
                .map(CommentReactionJpaEntity::toDomain);
    }

    @Override
    public Optional<CommentReaction> loadBoardReactionByType(Long commentId, Long memberId, UserReaction reactionType) {
        return repository.findByCommentIdAndMemberIdAndReactionType(commentId, memberId, reactionType)
                .map(CommentReactionJpaEntity::toDomain);
    }

    @Override
    public void removeBoardReaction(CommentReaction reaction) {

        repository.deleteById(reaction.getId());

    }
}
