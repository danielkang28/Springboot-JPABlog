package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto_increament
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    //대용량 데이터일때 사용
    private String content; //섬머노트 라이블러리. 일반적인 글이 디자인이 되어서 들어감. (어떻게?)_<html>태그가 섞여서 디자인이 됨.

    @ColumnDefault("0")
    private int count;

    @ManyToOne  //Many == Board, User == One    <- 한명의 유저가 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name = "userId")
    private User user;     //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
