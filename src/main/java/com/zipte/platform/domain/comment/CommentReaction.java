package com.zipte.platform.domain.comment;

import com.zipte.platform.domain.board.UserReaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.user.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentReaction {

    private Long id;
    private Member member;
    private Comment comment;
    private UserReaction reactionType;

    // 생성자
    public static CommentReaction of(Comment comment, Member member, UserReaction reactionType) {
        return CommentReaction.builder()
                .comment(comment)
                .member(member)
                .reactionType(reactionType)
                .build();
    }
}
