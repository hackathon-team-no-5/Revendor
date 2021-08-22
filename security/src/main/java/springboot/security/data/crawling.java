package springboot.security.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
/*
@SpringBootApplication
public class crawling {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(crawling.class, args)) {
            crawling m = ctx.getBean(crawling.class);
            m.method();
        }
    }

    @Autowired
    private JdbcTemplate jdbc;

    public void method() {
        List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM total");
        list.forEach(System.out::println);
    }
}

 */