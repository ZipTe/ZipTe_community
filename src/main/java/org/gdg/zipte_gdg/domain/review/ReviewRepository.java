package org.gdg.zipte_gdg.domain.review;

import org.gdg.zipte_gdg.domain.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN r.member m WHERE m.id = :memberId")
    Page<Review> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("select c from Comment c join c.review r where c.review.id = :reviewId")
    List<Comment> findCommentsWithReview(@Param("reviewId") Long reviewId);

    @Query("select ri from ReviewImage ri join Review r on ri.review.id = :reviewId")
    List<ReviewImage> selectReviewImages(@Param("reviewId") Long reviewId);

    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id WHERE ri.ord = 0 OR ri.ord IS NULL")
    Page<Object[]> selectList(Pageable pageable);

    @Query("select ri from ReviewImage ri join Review r on ri.review.id = :reviewId where ri.ord=0 OR ri.ord IS NULL")
    ReviewImage selectReviewImagesthumbnail(@Param("reviewId") Long reviewId);


}
