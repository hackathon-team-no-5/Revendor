package springboot.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.security.model.Board;
import springboot.security.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    Page<Item> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Item> findByTitleContaining(String title, Pageable pageable);
}
