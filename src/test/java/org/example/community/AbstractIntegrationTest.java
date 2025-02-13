package org.example.community;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <E> E find(Class<E> entityClass, Object primaryKey) {
        return entityManager.find(entityClass, primaryKey);
    }

    protected <T> T persist(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();
        return entity;
    }

    protected <T> Iterable<T> persistAll(Iterable<T> entities) {
        for (T entity : entities) {
            entityManager.persist(entity);
            entityManager.flush();
        }
        entityManager.clear();
        return entities;
    }
}

