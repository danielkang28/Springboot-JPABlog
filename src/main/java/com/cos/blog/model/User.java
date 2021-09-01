package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    //빌더 패턴!(?)
// ORM == Java(or 다른언어)에 있는  Object -> 테이블로 매핑해줌
@Entity //User Class를 통해서 읽어서 자동으로 My Sql에 테이블이 생성됨
//@DynamicInsert  //insert 할때 null인 필드 제외하고 insert
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

    //@ColumnDefault("'user'")    // <- 안에 따옴표가 하나 들어감. sql로 넘겼을때도 텍스트로 인식 해야하기 때문에...
    //DB는 RoleType 이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role;    //Enum을 쓰는게 좋다. 도메인을 만들어 줄 수 있음. 도메인. Ex. 초등학교의 도메인은 1~6

    @CreationTimestamp  //시간이 자동으로 입력된다.
    private Timestamp createDate;
}
