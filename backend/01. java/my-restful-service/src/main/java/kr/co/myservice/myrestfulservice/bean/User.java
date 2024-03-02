package kr.co.myservice.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Date joinDate;
}
