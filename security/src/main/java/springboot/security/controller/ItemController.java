package springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.security.model.Board;
import springboot.security.model.Item;
import springboot.security.repository.ItemRepository;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 20) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = itemRepository.findAll(pageable);
        Page<Item> items = itemRepository.findByTitleContaining(searchText, pageable);
        int startPage = Math.max(1, items.getPageable().getPageNumber() - 4);
        int endPage = Math.min(items.getTotalPages(), items.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", items);
        System.out.println("출력이 왜 안되나요?");
        for (Item b : items) {
            System.out.println("id: " + b.getId());
            System.out.println(" title: " + b.getTitle());
            System.out.println(" price: " + b.getPrice());
            System.out.println(" location: " + b.getLocation());
            System.out.println(" url: " + b.getUrl());

        }
        return "../static/src/html/buyPage";
    }
}
