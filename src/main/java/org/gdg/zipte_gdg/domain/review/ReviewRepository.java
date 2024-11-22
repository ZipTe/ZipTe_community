package org.gdg.zipte_gdg.domain.review;

import org.gdg.zipte_gdg.domain.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 멤버별 리뷰 조회
    @Query("SELECT r FROM Review r JOIN r.member m WHERE m.id = :memberId")
    Page<Review> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 썸네일
    @Query("select ri from ReviewImage ri join Review r on ri.review.id = :reviewId where ri.ord=0 OR ri.ord IS NULL")
    ReviewImage selectReviewImagesthumbnail(@Param("reviewId") Long reviewId);

    // 특정 아파트별 전체 가져오기
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId")
    Page<Object[]> selectListbyAptId(@Param("aptId") Long aptId, Pageable pageable);

    // 특정 아파트별 전체 조회수 높은 순서 정렬
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId ORDER BY r.viewCount DESC")
    Page<Object[]> selectListbyAptIdOrderByCountViewDesc(@Param("aptId") Long aptId, Pageable pageable);

    // 특정 아파트별 전체 좋아요 높은 순서 정렬
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId ORDER BY r.rating.rating DESC")
    Page<Object[]> selectListbyAptIdOrderByRatingDesc(@Param("aptId") Long aptId, Pageable pageable);

    // ---------- 양방향 설정으로 필요없는 것 --------------

//    // 전체 가져오기
//    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id WHERE ri.ord = 0 OR ri.ord IS NULL")
//    Page<Object[]> selectList(Pageable pageable);

    //    // 댓글도 가져오기
//    @Query("select c from Comment c join c.review r where c.review.id = :reviewId")
//    List<Comment> findCommentsWithReview(@Param("reviewId") Long reviewId);

//    // 리뷰에대해서 이미지 가져오기
//    @Query("select ri from ReviewImage ri join Review r on ri.review.id = :reviewId")
//    List<ReviewImage> selectReviewImages(@Param("reviewId") Long reviewId);





}
