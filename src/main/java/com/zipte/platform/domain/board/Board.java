package com.zipte.platform.domain.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.user.Member;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;
    private Member member;
    private BoardSnippet snippet;
    private BoardStatistics statistics;

    // 생성자
    public static Board of(Long id, Member member, BoardSnippet snippet, BoardStatistics statistics) {

        return Board.builder()
                .id(id)
                .member(member)
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

    // 양방향
    public void addComment() {
        this.getStatistics().addCommentCount();
    }

    public void addLikeReaction() {
        this.getStatistics().addLikeCount();
    }


    public void removeLikeReaction() {
        this.getStatistics().removeLikeCount();
    }
//
//
//    // 좋아요 수 계산 메서드
//    public Long getLikeCount() {
//        return reactions.stream()
//                .filter(reaction -> reaction.getReactionType() == UserReaction.LIKE)
//                .count();
//    }
//
//    // 싫어요 수 계산 메서드
//    public Long getDisLikeCount() {
//        return reactions.stream()
//                .filter(reaction -> reaction.getReactionType() == UserReaction.DISLIKE)
//                .count();
//    }
//
//    // 좋아요 - 싫어요 결과 계산
//    public Long getReactionScore() {
//        return getLikeCount() - getDisLikeCount();
//    }
//




}
