package springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.security.model.Board;
import springboot.security.repository.BoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

//    @Autowired
//    private BoardValidator boardValidator;


    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContaining(searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
//        System.out.println("출력이 왜 안되나요?");
//        for (Board b : boards) {
//            System.out.println("id: " + b.getId());
//            System.out.println(" title: " + b.getTitle());
//            System.out.println(" price: " + b.getPrice());
//
//        }
        return "../static/src/html/buyPage";
    }

//    @GetMapping("/list/test")
//    public String listTest(Model model, @PageableDefault(size = 10) Pageable pageable,
//                       @RequestParam(required = false, defaultValue = "") String searchText) {
////        Page<Board> boards = boardRepository.findAll(pageable);
//        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
//        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
//        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("boards", boards);
//        return "../static/src/html/luxuryItem";
//    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "../static/src/html/sellPage";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute Board board) {
//        boardValidator.validate(board, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "board/form";
//        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}