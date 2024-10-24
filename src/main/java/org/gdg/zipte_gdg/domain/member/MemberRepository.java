package org.gdg.zipte_gdg.domain.member;

import org.gdg.zipte_gdg.domain.review.Comment;
import org.gdg.zipte_gdg.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select r from Member m join m.reviews r where m.id = :memberId")
    List<Review> findReviewsByMemberId(@Param("memberId") Long memberId);

    @Query("select r from Member m join m.comments r where m.id = :memberId")
    List<Comment> findCommentsByMemberId(@Param("memberId") Long memberId);

}
