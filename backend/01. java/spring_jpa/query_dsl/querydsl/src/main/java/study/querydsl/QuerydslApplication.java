package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuerydslApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuerydslApplication.class, args);
  }


  //Bean으로 생성해서 사용함
  //동시성 문제 없음(싱글턴 패턴)
//  @Bean
//  JPAQueryFactory jpaQueryFactory(EntityManager em) {
//    return new JPAQueryFactory(em);
//  }
}
