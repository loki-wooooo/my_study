package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {

    fun findByName(username: String): User?

    // 같은데이터는 제거함 - 중복제거 distinct
//    @Query("select distinct u from User u left join fetch u.userLoanHistories")
//    fun findAllWithHistories(): List<User>
}