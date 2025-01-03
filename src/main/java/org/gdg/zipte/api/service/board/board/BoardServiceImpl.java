package org.gdg.zipte.api.service.board.board;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.service.board.board.response.BoardResponseWithComment;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.board.BoardImage;
import org.gdg.zipte.domain.board.board.BoardRepository;
import org.gdg.zipte.domain.board.category.BoardCategory;
import org.gdg.zipte.domain.board.category.BoardCategoryRepository;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySet;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySetRepository;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardCategorySetRepository boardCategorySetRepository;
    private final BoardRepository boardRepository;
    private final BoardImageService boardImageService;
    private final MemberRepository memberRepository;

    @Override
    public BoardResponse create(BoardRequest request) {

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow();

        // 게시물 생성
        Board newBoard = Board.of(request.getTitle(), request.getContent(), member);
        Board board = boardRepository.save(newBoard);

        // 카테고리와 연결
        BoardCategory category = boardCategoryRepository.findById(request.getCategoryId()).orElseThrow();
        BoardCategorySet boardCategorySet = BoardCategorySet.of(board, category);
        BoardCategorySet categorySet = boardCategorySetRepository.save(boardCategorySet);

        // 상품 사진
        List<String> uploads = boardImageService.saveFiles(board, request.getFiles());

        BoardResponse boardResponse = BoardResponse.from(categorySet);
        boardResponse.setUploadFileNames(uploads);

        return boardResponse;
    }

    @Override
    public PageResponseDto<BoardResponse> findAll(Long id, PageRequestDto pageRequestDto) {

        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(id);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 게시글 조회
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = boardCategorySetRepository.findProductCategoriesByIds(categoryIds, pageable);

        // 3. 결과 리스트 변환
        List<BoardResponse> dtoList = result.get().map(arr -> {
            BoardCategorySet board = (BoardCategorySet) arr[0];
            BoardImage boardImage = (BoardImage) arr[1];

            String imageStr = (boardImage != null) ? boardImage.getFileName() : "No image found";
            BoardResponse dto = BoardResponse.from(board);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

    private List<Long> findAllChildCategoryIds(Long id) {
        List<Long> result = new ArrayList<>();
        BoardCategory category = boardCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));

        result.add(category.getId()); // 현재 카테고리 ID 추가
        for (BoardCategory child : category.getChildren()) {
            result.addAll(findAllChildCategoryIds(child.getId())); // 재귀 호출로 자식 카테고리 ID 추가
        }
        return result;
    }


    @Override
    public PageResponseDto<BoardResponse> findFavorite(PageRequestDto pageRequestDto) {
        return null;
    }


    @Override
    public BoardResponseWithComment findOne(Long boardId) {
        return null;
    }

}
