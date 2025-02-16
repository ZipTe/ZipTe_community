package com.zipte.platform.application.service.apt;

import com.zipte.platform.application.port.in.apt.GetAptUseCase;
import com.zipte.platform.application.port.in.apt.RemoveAptUseCase;
import com.zipte.platform.application.port.out.apt.LoadAptPort;
import com.zipte.platform.domain.apt.Apt;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AptService implements GetAptUseCase, RemoveAptUseCase {

    private final LoadAptPort loadAptPort;

    @Override
    public Optional<Apt> getAptById(String id) {
        return Optional.empty();
    }

    @Override
    public Page<Apt> getAptByReview(Pageable pageable) {
        return null;
    }
}
