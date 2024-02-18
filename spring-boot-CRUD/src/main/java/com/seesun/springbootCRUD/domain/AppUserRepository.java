package com.seesun.springbootCRUD.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받아 쿼리문을 작성하지 않아도 기본적인 CRUD가 가능하다.
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
