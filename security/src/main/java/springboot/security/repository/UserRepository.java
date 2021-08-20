package springboot.security.repository;

import springboot.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository 를 상속하면 자동 컴포넌트 스캔(@Repository라는 어노테이션이 없어도 IOC 가능)
public interface UserRepository extends JpaRepository<User, Integer>{
    //findBy규칙 -> Username 문법
    //select* from user where username = 1?

    // Jpa Naming 전략
    // SELECT * FROM user WHERE username = 1?
    public User findByUsername(String username); // Jpa Query methods

    // SELECT * FROM user WHERE username = 1? AND password = 2?
    // User findByUsernameAndPassword(String username, String password);

    // @Query(value = "select * from user", nativeQuery = true)
    // User find마음대로();
}