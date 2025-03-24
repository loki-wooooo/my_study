package com.group.libraryapp.domain.book

import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BookRepository : JpaRepository<Book, Long> {

    // kotlin에서 ?를 통해 optional을 제외 할 수 있음
    fun findByName(bookName: String): Book?

    @Query("select new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, count(b.id)) from Book b group by b.type")
    fun getStats(): List<BookStatResponse>
}