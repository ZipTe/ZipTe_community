package com.eedo.project.zipte.infrastructure.out.persistence.jpa.user;

import com.eedo.project.zipte.domain.user.SavedAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface SavedAddressRepository extends JpaRepository<SavedAddress, Long> {

    // 특정 회원의 배송지 목록 조회
    List<SavedAddress> findByMemberId(Long memberId);



}
