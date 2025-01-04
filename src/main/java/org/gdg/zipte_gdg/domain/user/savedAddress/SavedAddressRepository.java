package org.gdg.zipte_gdg.domain.user.savedAddress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface SavedAddressRepository extends JpaRepository<SavedAddress, Long> {

    // 특정 회원의 배송지 목록 조회
    List<SavedAddress> findByMemberId(Long memberId);



}
