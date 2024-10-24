package org.gdg.zipte_gdg.domain.member;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.gdg.zipte_gdg.domain.review.Comment;
import org.gdg.zipte_gdg.domain.review.CommentRepository;
import org.gdg.zipte_gdg.domain.review.Review;
import org.gdg.zipte_gdg.domain.review.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private CommentRepository commentRepository;

    @Test
    @DisplayName("아이디로 게시글 찾기")
    public void findReviewsByMemberId() throws Exception{
        //given

        Address address = Address.newAddress("성남시", "장미로55", 13441);
        Member member = Member.createNewMember("test@naver.com", "test", "1111", "000-0000-0000", address);
        Member saveMember = memberRepository.save(member);


        Review review1 = Review.addNewReview(saveMember, "테스트1", "테스트1입니다");
        Review review2 = Review.addNewReview(saveMember, "테스트2", "테스트2입니다");
        Review saveReview1 = reviewRepository.save(review1);
        Review saveReview2 = reviewRepository.save(review2);

        //when
        List<Review> reviewsByMemberId = memberRepository.findReviewsByMemberId(saveMember.getId());

        //then
        reviewsByMemberId.forEach(reviews ->{
            Assertions.assertThat(reviews.getMember().getId()).isEqualTo(saveMember.getId());
        } );


        Assertions.assertThat(reviewsByMemberId).hasSize(2);
    }


    @Test
    @DisplayName("아이디별 댓글 확인하기")
    public void findCommentsByMemberId () throws Exception{

        //given
        Address address = Address.newAddress("성남시", "장미로55", 13441);

        Member member = Member.createNewMember("test@naver.com", "test", "1111", "000-0000-0000", address);
        Member member2 = Member.createNewMember("test2@naver.com", "test2", "2345", "111-1111-1111", address);

        Member saveMember = memberRepository.save(member);
        Member otherMember = memberRepository.save(member2);

        Review review1 = Review.addNewReview(saveMember, "테스트1", "테스트1입니다");
        reviewRepository.save(review1);

        Comment comment1 = Comment.addNewComment(review1, saveMember, "댓글테스트1");
        Comment comment2 = Comment.addNewComment(review1, saveMember, "댓글테스트1");
        Comment comment3 = Comment.addNewComment(review1, otherMember, "댓글테스트2");
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        //when
        List<Comment> commentsByMemberId = memberRepository.findCommentsByMemberId(saveMember.getId());

        //then
        Assertions.assertThat(review1.getComments()).hasSize(3);
        Assertions.assertThat(commentsByMemberId).hasSize(2);

        Assertions.assertThat(review1.getComments().get(0).getMember())
                .extracting(Member::getId)
                .isEqualTo(saveMember.getId());

        Assertions.assertThat(review1.getComments().get(2).getMember())
                .extracting(Member::getId)
                .isEqualTo(otherMember.getId());


        commentsByMemberId.forEach(comment -> {
            Assertions.assertThat(comment.getMember().getId()).isEqualTo(saveMember.getId());
        });
    }

}