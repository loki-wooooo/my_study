package com.group.libraryapp.dto.user.request;

public class UserUpdateRequest {

  public UserUpdateRequest(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
