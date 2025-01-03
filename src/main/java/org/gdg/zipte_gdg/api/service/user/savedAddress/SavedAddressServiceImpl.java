package org.gdg.zipte_gdg.api.service.user.savedAddress;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.user.savedAddress.request.SavedAddressRequest;
import org.gdg.zipte_gdg.api.service.user.savedAddress.response.SavedAddressResponse;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.gdg.zipte_gdg.domain.user.member.Member;
import org.gdg.zipte_gdg.domain.user.member.MemberRepository;
import org.gdg.zipte_gdg.domain.user.savedAddress.SavedAddress;
import org.gdg.zipte_gdg.domain.user.savedAddress.SavedAddressRepository;
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

        return SavedAddressResponse.of(savedAddressRepository.save(savedAddress));
    }

    @Override
    public List<SavedAddressResponse> findById(Long memberId) {
        List<SavedAddress> result = savedAddressRepository.findByMemberId(memberId);
        return SavedAddressResponse.ofs(result);
    }

    @Override
    public SavedAddressResponse getOne(Long Id) {
        SavedAddress savedAddress = savedAddressRepository.findById(Id).orElseThrow();
        return SavedAddressResponse.of(savedAddress);
    }

}
