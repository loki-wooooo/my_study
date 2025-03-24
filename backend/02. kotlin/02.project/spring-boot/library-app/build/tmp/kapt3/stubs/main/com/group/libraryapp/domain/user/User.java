package com.group.libraryapp.domain.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001f"}, d2 = {"Lcom/group/libraryapp/domain/user/User;", "", "id", "", "name", "", "age", "", "userLoanHistories", "", "Lcom/group/libraryapp/domain/user/loanhistory/UserLoanHistory;", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUserLoanHistories", "()Ljava/util/List;", "loanBook", "", "book", "Lcom/group/libraryapp/domain/book/Book;", "returnBook", "bookName", "updateName", "library-app"})
@javax.persistence.Entity
public final class User {
    @org.jetbrains.annotations.Nullable
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Id
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer age = null;
    @org.jetbrains.annotations.NotNull
    @javax.persistence.OneToMany(mappedBy = "user", cascade = {javax.persistence.CascadeType.ALL}, orphanRemoval = true)
    private final java.util.List<com.group.libraryapp.domain.user.loanhistory.UserLoanHistory> userLoanHistories = null;
    
    public User(@org.jetbrains.annotations.Nullable
    java.lang.Long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Integer age, @org.jetbrains.annotations.NotNull
    java.util.List<com.group.libraryapp.domain.user.loanhistory.UserLoanHistory> userLoanHistories) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.group.libraryapp.domain.user.loanhistory.UserLoanHistory> getUserLoanHistories() {
        return null;
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
    }
    
    public final void loanBook(@org.jetbrains.annotations.NotNull
    com.group.libraryapp.domain.book.Book book) {
    }
    
    public final void returnBook(@org.jetbrains.annotations.NotNull
    java.lang.String bookName) {
    }
}