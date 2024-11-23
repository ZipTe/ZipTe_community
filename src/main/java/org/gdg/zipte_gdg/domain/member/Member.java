package org.gdg.zipte_gdg.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.comment.Comment;
//import org.gdg.zipte_gdg.domain.like.CommentLike;
//import org.gdg.zipte_gdg.domain.like.ReviewLike;
import org.gdg.zipte_gdg.domain.rating.Rating;
import org.gdg.zipte_gdg.domain.review.Review;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;

    // 한번에 게시글 조회를 위해서
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    // 한번에 작성한 댓글 조회를 위해서
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    // 평점 준 목록
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Rating> ratings = new ArrayList<>();

//    // 하트 준 목록
//    @OneToMany(mappedBy = "member")
//    @Builder.Default
//    private List<ReviewLike> reviewLikes = new ArrayList<>();
//
//    // 하트 준 목록
//    @OneToMany(mappedBy = "member")
//    @Builder.Default
//    private List<CommentLike> commentLikes = new ArrayList<>();

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @CreatedDate
    private LocalDateTime createdAt;

    // 로직
    public static Member createNewMember(String email, String username, String password, String phoneNumber, Address address) {
        return Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .address(address)
                .memberRole(MemberRole.GREEN)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 게시물 추가 로직
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    // 댓글 추가 로직
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    // 주소 추가 로직
    public void addAddress(Address address) {
        this.address = address;
    }

    // ROLE 관련 로직
    public void changeMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    // 이메일 추가 로직
    public void changeEmail(String email) {
        this.email = email;
    }


//    // --------------------------------- 양방향 필요한가
//    // 리뷰 좋아요 추가 로직
//    public void addReviewLike(ReviewLike reviewLike) {
//        this.reviewLikes.add(reviewLike);
//    }
//
//    // 댓글 좋아요 추가 로직
//    public void addCommentLike(CommentLike commentLike) {
//        this.commentLikes.add(commentLike);
//    }

}