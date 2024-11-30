package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import javax.persistence.*

@Entity
class User(

    // property 설정시 getter 자동
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    val age: Int?,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름이 비어 있을 수 없습니다.")
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun loanBook(book: Book) {
        this.userLoanHistories.add(UserLoanHistory(null, this, book.name, UserLoanStatus.LOADED))
    }

    fun returnBook(bookName: String) {
        //내가 갖고있는 도서 목록 중 첫번째 책 이름을 찾아서 바로 리턴처리
        // 조건을 만족하는 첫 번째 요소 반환
        this.userLoanHistories.first { history -> history.bookName == bookName }.doReturn()
    }
}