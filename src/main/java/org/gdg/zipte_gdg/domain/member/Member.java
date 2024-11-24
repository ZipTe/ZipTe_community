package org.gdg.zipte_gdg.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.comment.Comment;
import org.gdg.zipte_gdg.domain.rating.Rating;
import org.gdg.zipte_gdg.domain.review.Review;
import org.gdg.zipte_gdg.domain.role.Role;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

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

    @CreatedDate
    private LocalDateTime createdAt;

    // 로직
    public static Member createNewMember(String email, String username, String password, String phoneNumber, Address address) {
        List<Role> roles = new ArrayList<>();

        Member member = Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .address(address)
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();
        member.addMemberRole(Role.OAUTH_FIRST_JOIN);
        return member;
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

    public void addMemberRole(Role role) {
        if (!this.roles.contains(role)) { // 중복 방지
            this.roles.add(role);
        }
    }

    public void removeMemberRole(Role role) {
        this.roles.remove(role); // Role 객체를 직접 삭제
    }

    // 이메일 추가 로직
    public void changeEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // Role을 문자열로 변환
                .collect(Collectors.toList());
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