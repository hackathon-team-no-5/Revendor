package springboot.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.security.model.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    List<Board> findByTitleOrContent(String title, String content);
//    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Board> findByTitleContaining(String title, Pageable pageable);

}
