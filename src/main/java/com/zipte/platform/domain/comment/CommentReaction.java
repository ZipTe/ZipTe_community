package com.zipte.platform.domain.comment;

import com.zipte.platform.domain.board.UserReaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentReaction {

    private Long id;
    private Long memberId;
    private Comment comment;
    private UserReaction reactionType;

    // 생성자
    public static CommentReaction of(Comment comment, Long memberId, UserReaction reactionType) {
        CommentReaction reaction = CommentReaction.builder()
                .comment(comment)
                .memberId(memberId)
                .reactionType(reactionType)
                .build();

        checkUserReaction(comment, reactionType);

        return reaction;
    }

    // 메소드 따로 빼기
    private static void checkUserReaction(Comment comment, UserReaction reactionType) {
        if(reactionType == UserReaction.LIKE) {
            comment.getStatistics().addLikeCount();
        } else {
            comment.getStatistics().addDislikeCount();
        }
    }
}
