package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * 코틀린에서 트레일링 콤마란, 여러 요소가 쉼표로 구분된 목록의 마지막 항목 뒤에 오는 쉼표를 의미합니다. 코틀린 1.4 버전부터 이 기능이 도입되었습니다2. 트레일링 콤마는 선택 사항이며, 코드의 동작에는 영향을 미치지 않습니다1.
 * 트레일링 콤마의 장점
 * 버전 관리의 편리함: 코드 변경 시, 특히 요소를 추가하거나 순서를 변경할 때, 트레일링 콤마가 있으면 변경된 부분만 신경 쓰면 되므로 버전 관리 도구에서의 차이가 더 명확하게 보입니다12.
 * 코드 가독성 향상: 여러 줄로 나뉘어진 긴 매개변수 목록이나 데이터 클래스의 속성 목록을 작성할 때, 트레일링 콤마를 사용하면 요소를 추가하거나 제거할 때 실수로 콤마를 빠뜨릴 염려가 줄어듭니다2.
 * 코드 생성 단순화: 객체 초기화 시 마지막 요소 뒤에 쉼표를 추가하면 코드 생성이 더 간단해집니다1.
 * */
@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun cleanup() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작된다")
    fun saveBookTest() {
        //givne
        val request = BookRequest("이상한 나라의 엘리스", BookType.COMPUTER);

        //when
        bookService.saveBook(request);

        //then
        val books = bookRepository.findAll();
        assertThat(books).hasSize(1);
        assertThat(books[0].name).isEqualTo("이상한 나라의 엘리스");
    }

    @Test
    @DisplayName("책 대출이 정상 동작된다.")
    fun loadBookTest() {
        //given
        bookRepository.save(Book.fixture("이상한 나라의 엘리스"))
        val savedUser = userRepository.save(User(null, "admin", null))
        val request = BookLoanRequest("admin", "이상한 나라의 엘리스")

        //when
        bookService.loanBook(request)

        //then
        val results = userLoanHistoryRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results[0].bookName).isEqualTo("이상한 나라의 엘리스")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.LOADED)
    }

    @Test
    @DisplayName("책이 진짜 대출되어있다면, 신규 대출이 실패한다.")
    fun loanBookFailTest() {
        //given
        //default paarameter를 인식함
        bookRepository.save(Book.fixture("이상한 나라의 엘리스"))
        val savedUser = userRepository.save(User(null, "admin", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(null, savedUser, "이상한 나라의 엘리스"))
        val request = BookLoanRequest("admin", "이상한 나라의 엘리스")

        //when&then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책 입니다.")
    }

    @Test
    @DisplayName("책 반납이 정상적으로 동작한다.")
    fun returnBookTest() {
        //given
        bookRepository.save(Book.fixture("이상한 나라의 엘리스"))
        val savedUser = userRepository.save(User(null, "admin", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(null, savedUser, "이상한 나라의 엘리스"))
        val request = BookReturnRequest("admin", "이상한 나라의 엘리스")

        //when
        bookService.returnBook(request);

        //then
        val results = userLoanHistoryRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results[0].status).isEqualTo(UserLoanStatus.RETURNED)
    }

    @Test
    @DisplayName("책 대여 권수를 정상 확인한다.")
    fun countLoanedBookTest() {
        //given
        val savedUser = userRepository.save(User(null, "최태현", null))
        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(savedUser.id, savedUser, "A"),
                UserLoanHistory.fixture(savedUser.id, savedUser, "B", UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(savedUser.id, savedUser, "C", UserLoanStatus.RETURNED),
            )
        )

        //when
        val result = bookService.countLoanedBook();

        //then
        //대여 중인 책만 나옴
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다.")
    fun getBookStatisticsTest() {
        //given
        bookRepository.saveAll(
            listOf(
                Book.fixture("A", BookType.COMPUTER),
                Book.fixture("B", BookType.COMPUTER),
                Book.fixture("C", BookType.SCIENCE),
            )
        )

        //when
        val result = bookService.getBookStatistics();

        //then
        assertThat(result).hasSize(2);
        assertCount(result, BookType.COMPUTER, 2L);
        assertCount(result, BookType.SCIENCE, 1L);

//        val computerDto = result.first { result -> result.type == BookType.COMPUTER }
//        assertThat(computerDto.count).isEqualTo(2)
//
//        val scienceDto = result.first { result -> result.type == BookType.SCIENCE }
//        assertThat(scienceDto.count).isEqualTo(1)
    }

    private fun assertCount(results: List<BookStatResponse>, type: BookType, checkCount: Long) {
        assertThat(results.filter { result -> result.type == type }.count()).isEqualTo(checkCount)
    }

}