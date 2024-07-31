package com.example.catalogservice.service;


import com.example.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    public Iterable<CatalogEntity> getAllCatalogs() throws Exception;
}
