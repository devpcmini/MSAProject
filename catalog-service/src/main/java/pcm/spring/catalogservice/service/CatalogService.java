package pcm.spring.catalogservice.service;

import pcm.spring.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
