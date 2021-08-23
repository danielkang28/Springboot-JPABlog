package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
    @Id     //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     //시퀀스, auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne      //Many = Reply, One = board
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp creatDate;
}