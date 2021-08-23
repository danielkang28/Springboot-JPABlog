package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    //EAGER == 데이터를 무조건 다 들고옴.
    //Lazy == 데이터를 바로 다 들고오지 않음. OneToMany 의 기본전략
    @ManyToOne(fetch = FetchType.EAGER)  //Many == Board, User == One    <- 한명의 유저가 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name = "userId")
    private User user;     //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  //mappebBy == 연관관계의 주인이 아니다.(난 FK가 아니다) DB에 칼럼을 만들지 마세요. 나는 그냥 board를 셀렉트 할때 join문을 얻기 위해서 필요한 겁니다
//    @JoinColumn(name = "replyId") <- 조인컬럼을 사용하지 않는다. 테이블에 포린키가 들어가면 댓글을 여러게 달면 원자성(하나의 데이터에는 여러개가 들어갈 수 없음)이 꺠짐.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}