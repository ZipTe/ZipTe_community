package org.gdg.zipte.domain.review.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 멤버별 리뷰 조회
    @Query("SELECT r, ri  FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id WHERE r.member.id = :memberId and ri.ord=0 OR ri.ord IS NULL")
    Page<Object[]> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 특정 아파트별 전체 가져오기
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId")
    Page<Object[]> selectListbyAptId(@Param("aptId") Long aptId, Pageable pageable);

    // 특정 아파트별 전체 조회수 높은 순서 정렬
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId ORDER BY r.viewCount DESC")
    Page<Object[]> selectListbyAptIdOrderByCountViewDesc(@Param("aptId") Long aptId, Pageable pageable);

    // 특정 아파트별 전체 좋아요 높은 순서 정렬
    @Query("SELECT r, ri FROM Review r LEFT JOIN ReviewImage ri ON r.id = ri.review.id LEFT JOIN Apt a ON r.apt.id = a.id WHERE (ri.ord = 0 OR ri.ord IS NULL) AND r.apt.id = :aptId ORDER BY r.rating.overallRating DESC")
    Page<Object[]> selectListbyAptIdOrderByRatingDesc(@Param("aptId") Long aptId, Pageable pageable);




}
