package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Timestamp;

@Entity //User Class를 통해서 읽어서 자동으로 My Sql에 테이블이 생성됨
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id; // 시퀀스, auto_invrement

    @Column(nullable = false, length = 30)
    private String username;   //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("user")
    private String role;    //Enum을 쓰는게 좋다. 도메인을 만들어 줄 수 있음. 도메인. Ex. 초등학교의 도메인은 1~6
    //admin, user, manager 등

    @CreationTimestamp  //시간이 자동으로 입력된다.
    private Timestamp createDate;
}
