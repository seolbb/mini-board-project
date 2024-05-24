package org.example.board_project.service;

import lombok.RequiredArgsConstructor;
import org.example.board_project.domain.Board;
import org.example.board_project.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 목록 보기
    @Transactional(readOnly = true)
    public Iterable<Board> findAll(){
        return boardRepository.findAll();
    }

    // 페이징 처리된 게시글 목록 조회
    @Transactional(readOnly = true)
    public Page<Board> findAll(Pageable pageable){
        Pageable sortedByDescId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));   // id를 기준으로 desc
        return boardRepository.findAll(sortedByDescId);
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public Board findById(Long id){
        return boardRepository.findById(id).orElse(null);
    }

    // 게시글 등록
    @Transactional
    public Board save(Board board){
        return boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    // 게시글 수정
    @Transactional
    // TODO : 페이징 처리해주기
    public Board update(Board board){
        return boardRepository.save(board);
    }
}
