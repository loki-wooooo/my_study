package com.group.libraryapp.domain.book

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BookRepository : JpaRepository<Book, Long> {

    // kotlin에서 ?를 통해 optional을 제외 할 수 있음
    fun findByName(bookName: String): Book?
}