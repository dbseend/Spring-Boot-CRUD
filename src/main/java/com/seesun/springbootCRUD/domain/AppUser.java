package com.seesun.springbootCRUD.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 데이터베이스 테이블과 매핑되는 객체
@Getter // Getter 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@Table(name = "app_user") // 테이블 이름 지정, 없으면 클래스 이름을 테이블 이름으로 사용
public class AppUser {

    @Id // 기본키 지정(필수)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 내용 추가 시 자동으로 증가
    private Long id;

    private String username;

    private Integer age;

    private String phoneNumber;

    @Builder // 생성자 대신 사용, 인자 순서를 지키지 않아도 됨
    public AppUser(String username, Integer age, String phoneNumber) {
        this.username = username;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void update(String username, Integer age, String phoneNumber) {
        this.username = username;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
}
