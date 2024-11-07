package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val user: User,

    val bookName: String,

    var isReturn: Boolean,

    ) {

    fun doReturn() {
        this.isReturn = true
    }

}