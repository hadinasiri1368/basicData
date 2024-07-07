package org.basicData.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class JPA<ENTITY, ID> {
    private final CacheRepository dataCacheService;
    @Autowired
    public JPA(@Lazy CacheRepository dataCacheService) {
        this.dataCacheService = dataCacheService;
    }
    @PersistenceContext()
    private EntityManager entityManager;

    @Transactional
    public void save(ENTITY entity) throws Exception {
        CommonUtils.setNull(entity);
        entityManager.persist(entity);
        Class<?> entityClass = entity.getClass();
        dataCacheService.refreshData(entityClass);
    }

    @Transactional
    public void update(ENTITY entity) throws Exception {
        CommonUtils.setNull(entity);
        entityManager.merge(entity);
        Class<?> entityClass = entity.getClass();
        dataCacheService.refreshData(entityClass);
    }

    @Transactional
    public void remove(ENTITY entity) {
        entityManager.remove(entityManager.merge(entity));
        Class<?> entityClass = entity.getClass();
        dataCacheService.refreshData(entityClass);
    }

    @Transactional
    public int remove(Long id, Class<ENTITY> aClass) {
        Entity entity = aClass.getAnnotation(Entity.class);
        int returnValue = entityManager.createQuery("delete  " + entity.name() + " o where o.id=:id").setParameter("id", id).executeUpdate();
        if (returnValue > 0) {
            dataCacheService.refreshData(aClass);
        }
        return returnValue;
    }

    public ENTITY findOne(Class<ENTITY> aClass, ID id) {
        List<ENTITY> list = dataCacheService.findAll(aClass);
        if (!CommonUtils.isNull(list))
            return CommonUtils.findById(list, (Long) id);
        return entityManager.find(aClass, id);
    }

    public List<ENTITY> findAll(Class<ENTITY> aClass) {
        List<ENTITY> list = dataCacheService.findAll(aClass);
        if (!CommonUtils.isNull(list))
            return list;
        Entity entity = aClass.getAnnotation(Entity.class);
        Query query = entityManager.createQuery("select entity from " + entity.name() + " entity");
        list = query.getResultList();
        dataCacheService.saveOrUpdateToCache(aClass.getName(), list);
        return list;
    }

    public Page<ENTITY> findAllWithPaging(Class<ENTITY> aClass) {
        List<ENTITY> list = findAll(aClass);
        PageRequest pageRequest = PageRequest.of(0, list.isEmpty() ? 1 : list.size());
        return new PageImpl<ENTITY>(list, pageRequest, list.isEmpty() ? 1 : list.size());
    }

    public Page<ENTITY> findAllWithPaging(Class<ENTITY> aClass, PageRequest pageRequest) {
        List<ENTITY> list = findAll(aClass);
        return new PageImpl<ENTITY>(list, pageRequest, list.isEmpty() ? 1 : list.size());
    }
    public List findByNativeQuery(String sql){
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }
    public List findByQuery(String sql){
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
}
