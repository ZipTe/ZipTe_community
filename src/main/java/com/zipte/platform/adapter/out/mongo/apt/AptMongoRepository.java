package com.zipte.platform.adapter.out.mongo.apt;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AptMongoRepository extends MongoRepository<AptDocument, String> {
}
