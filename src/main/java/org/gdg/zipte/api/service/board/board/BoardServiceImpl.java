package org.gdg.zipte.api.service.board.board;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.board.BoardImage;
import org.gdg.zipte.domain.board.board.BoardRepository;
import org.gdg.zipte.domain.board.category.BoardCategory;
import org.gdg.zipte.domain.board.category.BoardCategoryRepository;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySet;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySetRepository;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Log4j2
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

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("해당 멤버가 존재하지 않습니다."));

        // 게시물 생성
        Board newBoard = Board.of(request.getTitle(), request.getContent(), member);
        Board board = boardRepository.save(newBoard);

        // 카테고리와 연결
        BoardCategory category = boardCategoryRepository.findById(request.getCategoryId()).orElseThrow();
        BoardCategorySet boardCategorySet = BoardCategorySet.of(board, category);
        BoardCategorySet categorySet = boardCategorySetRepository.save(boardCategorySet);

        // 게시물 사진
        List<String> uploads = boardImageService.saveFiles(board, request.getFiles());

        BoardResponse boardResponse = BoardResponse.from(categorySet);
        boardResponse.setUploadFileNames(uploads);

        return boardResponse;
    }

    @Override
    public PageResponse<BoardResponse> findByCategoryId(Long categoryId, PageRequest pageRequest) {

        // 1. 카테고리 ID와 자식 카테고리 ID 목록 생성
        List<Long> categoryIds = findAllChildCategoryIds(categoryId);

        // 2. 자식 카테고리를 포함한 ID 목록을 이용해 게시글 조회
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());
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
        return new PageResponse<>(dtoList, pageRequest, total);
    }

    private List<Long> findAllChildCategoryIds(Long id) {
        List<Long> result = new ArrayList<>();
        BoardCategory category = boardCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("카테고리가 존재하지 않습니다."));

        result.add(category.getId()); // 현재 카테고리 ID 추가
        for (BoardCategory child : category.getChildren()) {
            result.addAll(findAllChildCategoryIds(child.getId())); // 재귀 호출로 자식 카테고리 ID 추가
        }
        return result;
    }


    @Override
    public PageResponse<BoardResponse> findFavorite(PageRequest pageRequest) {
        return null;
    }


    @Override
    public BoardResponse findOne(Long boardId) {
        BoardCategorySet boardCategorySet = boardCategorySetRepository.findByBoardId(boardId)
                .orElseThrow(()-> new EntityNotFoundException("게시판이 존재하지 않습니다."));

        // 조회수 처리
        Board board = boardCategorySet.getBoard();
        board.addCount();
        boardRepository.save(board);
        BoardResponse boardResponse = BoardResponse.from(boardCategorySet);

        // 사진 처리
        List<BoardImage> boardImages = boardRepository.selectBoardImage(boardId);
        boardResponse.setUploadFileNames(boardImages.stream().map(BoardImage::getFileName).collect(Collectors.toList()));

        return boardResponse;
    }

}
