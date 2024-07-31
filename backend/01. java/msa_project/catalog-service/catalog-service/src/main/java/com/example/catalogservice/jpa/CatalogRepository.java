package com.example.catalogservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {

    public CatalogEntity findByProductId(final String productId) throws Exception;
}
