package com.eedo.project.zipte.application.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.representation.request.user.SavedAddressRequest;
import com.eedo.project.zipte.application.port.in.SavedAddressService;
import com.eedo.project.zipte.representation.response.SavedAddressResponse;
import com.eedo.project.zipte.domain.user.Address;
import com.eedo.project.zipte.domain.user.Member;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.user.MemberRepository;
import com.eedo.project.zipte.domain.user.SavedAddress;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.user.SavedAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class SavedAddressServiceImpl implements SavedAddressService {

    private final SavedAddressRepository savedAddressRepository;
    private final MemberRepository memberRepository;

    @Override
    public SavedAddressResponse create(SavedAddressRequest request) {
        log.info("222" + request.toString());
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow();

        Address address = Address.of(request.getStreetAddress(), request.getDetailAddress(), request.getZipcode());
        SavedAddress savedAddress = SavedAddress.of(member, address, request.getOrderDesc(), request.getDeliveryDesc(),request.getIsDefault());

        return SavedAddressResponse.from(savedAddressRepository.save(savedAddress));
    }

    @Override
    public List<SavedAddressResponse> findById(Long memberId) {
        List<SavedAddress> result = savedAddressRepository.findByMemberId(memberId);
        return SavedAddressResponse.froms(result);
    }

    @Override
    public SavedAddressResponse getOne(Long Id) {
        SavedAddress savedAddress = savedAddressRepository.findById(Id).orElseThrow();
        return SavedAddressResponse.from(savedAddress);
    }

}
