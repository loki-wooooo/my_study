package com.group.libraryapp.domain.user.loanhistory;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0019\u001a\u00020\u001aR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001c"}, d2 = {"Lcom/group/libraryapp/domain/user/loanhistory/UserLoanHistory;", "", "id", "", "user", "Lcom/group/libraryapp/domain/user/User;", "bookName", "", "status", "Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;", "(Ljava/lang/Long;Lcom/group/libraryapp/domain/user/User;Ljava/lang/String;Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;)V", "getBookName", "()Ljava/lang/String;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "isReturn", "", "()Z", "getStatus", "()Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;", "setStatus", "(Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;)V", "getUser", "()Lcom/group/libraryapp/domain/user/User;", "doReturn", "", "Companion", "library-app"})
@javax.persistence.Entity
public final class UserLoanHistory {
    @org.jetbrains.annotations.Nullable
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Id
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull
    @javax.persistence.ManyToOne
    private final com.group.libraryapp.domain.user.User user = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String bookName = null;
    @org.jetbrains.annotations.NotNull
    private com.group.libraryapp.domain.user.loanhistory.UserLoanStatus status;
    @org.jetbrains.annotations.NotNull
    public static final com.group.libraryapp.domain.user.loanhistory.UserLoanHistory.Companion Companion = null;
    
    public UserLoanHistory(@org.jetbrains.annotations.Nullable
    java.lang.Long id, @org.jetbrains.annotations.NotNull
    com.group.libraryapp.domain.user.User user, @org.jetbrains.annotations.NotNull
    java.lang.String bookName, @org.jetbrains.annotations.NotNull
    com.group.libraryapp.domain.user.loanhistory.UserLoanStatus status) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.group.libraryapp.domain.user.User getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBookName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.group.libraryapp.domain.user.loanhistory.UserLoanStatus getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.NotNull
    com.group.libraryapp.domain.user.loanhistory.UserLoanStatus p0) {
    }
    
    public final boolean isReturn() {
        return false;
    }
    
    public final void doReturn() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/group/libraryapp/domain/user/loanhistory/UserLoanHistory$Companion;", "", "()V", "fixture", "Lcom/group/libraryapp/domain/user/loanhistory/UserLoanHistory;", "id", "", "user", "Lcom/group/libraryapp/domain/user/User;", "bookName", "", "status", "Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;", "(Ljava/lang/Long;Lcom/group/libraryapp/domain/user/User;Ljava/lang/String;Lcom/group/libraryapp/domain/user/loanhistory/UserLoanStatus;)Lcom/group/libraryapp/domain/user/loanhistory/UserLoanHistory;", "library-app"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.group.libraryapp.domain.user.loanhistory.UserLoanHistory fixture(@org.jetbrains.annotations.Nullable
        java.lang.Long id, @org.jetbrains.annotations.NotNull
        com.group.libraryapp.domain.user.User user, @org.jetbrains.annotations.NotNull
        java.lang.String bookName, @org.jetbrains.annotations.NotNull
        com.group.libraryapp.domain.user.loanhistory.UserLoanStatus status) {
            return null;
        }
    }
}