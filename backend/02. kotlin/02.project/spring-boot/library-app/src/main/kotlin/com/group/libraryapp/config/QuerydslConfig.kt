package com.group.libraryapp.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

/**
 * JPA Query Factory 객체 생성 후 주입
 * */
@Configuration
class QuerydslConfig(
    private val em: EntityManager
) {

    @Bean
    fun querydsl(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}