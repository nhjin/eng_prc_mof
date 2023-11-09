package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="korean_sub")
public class KorSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "text_sub", nullable = false)
    private String textSub;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "chapter", nullable = false)
    private String chapter;
}
