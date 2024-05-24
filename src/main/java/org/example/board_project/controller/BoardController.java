package org.example.board_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.board_project.domain.Board;
import org.example.board_project.service.BoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 목록 보기
    @GetMapping("/list")
    public String getPostList(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page -1 , size);
        model.addAttribute("posts", boardService.findAll(pageable));
        model.addAttribute("currentPage", page);

        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/view")
    public String getPostDetail(@RequestParam Long id, @RequestParam(defaultValue = "1") int page, Model model){
        Board post = boardService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("currentPage", page);
        return "board/view";
    }

    // 게시글 등록
    // get
    @GetMapping("/writeform")
    public String createForm(Model model){
        model.addAttribute("post", new Board());
        return "board/writeform";
    }

    // post
    @PostMapping("/write")
    public String createPost(@ModelAttribute Board board){
        board.setCreatedAt(LocalDateTime.now()); // 현재 날짜를 등록일자로 설정
        board.setUpdatedAt(LocalDateTime.now()); // 현재 날짜를 수정일자로 설정
        boardService.save(board);
        return "redirect:/list";
    }

    // 게시글 삭제
    // get
    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam Long id, @RequestParam(defaultValue = "1") int page, Model model){
        model.addAttribute("id", id);
        model.addAttribute("currentPage", page);
        model.addAttribute("post", new Board());
        return "board/deleteform";
    }

    // post
    @PostMapping("/delete")
    public String deletePost(@ModelAttribute Board board, @RequestParam(defaultValue = "1") int page, Model model) {
        Board originBoard = boardService.findById(board.getId());
        if (originBoard != null && originBoard.getPassword().equals(board.getPassword())) { // 비밀번호 일치
            boardService.deleteById(board.getId());
            return "redirect:/list";
        } else {    // 비밀번호 불일치
            model.addAttribute("id", board.getId());
            model.addAttribute("currentPage", page);
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("post", board); // post 객체도 모델에 추가
            return "board/deleteform"; // 실패 시 삭제 폼으로 다시 리턴
        }
    }

    // 게시글 수정
    // get
    @GetMapping("/updateform")
    public String updateForm(@RequestParam Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("post", new Board());
        return "board/updateform";
    }

    // post
    @PostMapping("/update")
    public String updatePost(@ModelAttribute Board board, Model model){
        Board originBoard = boardService.findById(board.getId());
        if(board.getPassword() != null && originBoard.getPassword().equals(board.getPassword())){   // 비밀번호 일치
            board.setCreatedAt(originBoard.getCreatedAt()); // 등록날짜 board 객체에 다시 넣어줘야함
            board.setUpdatedAt(LocalDateTime.now()); // 현재 날짜를 수정일자로 설정
            boardService.save(board);
        } else {    // 비밀번호 불일치
            model.addAttribute("id", board.getId());
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("post", board); // post 객체도 모델에 추가
            return "board/updateform";
        }
        return "redirect:/view?id=" + board.getId();
    }

}
