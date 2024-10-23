package org.gdg.zipte_gdg.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.review.Comment;
import org.gdg.zipte_gdg.domain.review.Review;

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

    // 게시글 조회를 위해서
    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    // 작성한 댓글 조회를 위해서
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private LocalDateTime createdAt;

}
