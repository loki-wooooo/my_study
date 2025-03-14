package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

//    fun findByBookName(bookName: String): UserLoanHistory?
//
//    // null 대응
//    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?;
//
//    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
//
//    fun countByStatus(status: UserLoanStatus): Long
}