package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Transactional
    fun saveBook(request: BookRequest) {
        val book = Book(request.name, request.type)
        bookRepository.save(book);
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryRepository.findByBookNameAndStatus(request.bookName, UserLoanStatus.LOADED) != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책 입니다.")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
//        return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOADED).size
        return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOADED).toInt()
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {
        return bookRepository.getStats()

//        return bookRepository.findAll() // list<Book>
//            .groupBy { book -> book.type } // map<BookType, List<Book>>
//            .map { (type, books) -> BookStatResponse(type, books.size) } // list<BookStatResponse>

//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            val targetDto = results.firstOrNull { dto -> book.type == dto.type }
//            if (targetDto == null) {
//                results.add(BookStatResponse(book.type, 1))
//            } else {
//                targetDto.plusOne()
//            }
//
//        }
//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            /**
//             * 코틀린의 엘비스 연산자(Elvis Operator)는 null 체크와 기본값 할당을 간결하게 처리할 수 있는 유용한 기능입니다. 엘비스 연산자는 '?:'로 표현되며, 다음과 같은 특징을 가집니다:
//             * 기본 사용법
//             *  엘비스 연산자는 왼쪽 표현식이 null이 아닐 경우 해당 값을 반환하고, null일 경우 오른쪽 표현식을 반환합니다210.
//             * kotlin
//             *  val length = str?.length ?: -1
//             *  이 예제에서 str이 null이 아니면 str.length를 반환하고, null이면 -1을 반환합니다.
//             * */
//            val targetDto = results.firstOrNull { dto -> book.type == dto.type }?.plusOne()?: results.add(BookStatResponse(book.type, 1))
//
//        }
//        return results
    }

}