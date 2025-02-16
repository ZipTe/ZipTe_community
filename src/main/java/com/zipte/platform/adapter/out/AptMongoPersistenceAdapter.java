package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.mongo.apt.AptDocument;
import com.zipte.platform.adapter.out.mongo.apt.AptMongoRepository;
import com.zipte.platform.application.port.out.apt.LoadAptPort;
import com.zipte.platform.domain.apt.Apt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AptMongoPersistenceAdapter implements LoadAptPort{

    private final AptMongoRepository repository;

    @Override
    public Optional<Apt> loadApt(String id) {
        return repository.findById(id)
                .map(AptDocument::toDomain);
    }
}
