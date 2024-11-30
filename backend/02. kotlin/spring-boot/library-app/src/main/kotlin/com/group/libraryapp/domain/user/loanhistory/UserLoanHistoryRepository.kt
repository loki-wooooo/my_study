package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {
    
    // null 대응
    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?;
}