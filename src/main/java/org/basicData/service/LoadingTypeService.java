package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.model.LoadingType;
import org.basicData.repository.JPA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LoadingTypeService {
    @PersistenceContext
    EntityManager entityManager;

    private final JPA<LoadingType, Long> loadingTypeJPA;

    @Value("${PageRequest.page}")
    private Integer page;
    @Value("${PageRequest.size}")
    private Integer size;

    public LoadingTypeService(JPA<LoadingType, Long> loadingTypeJPA) {
        this.loadingTypeJPA = loadingTypeJPA;
    }

    @Transactional
    public void insert(LoadingType loadingType, Long userId) throws Exception {
        loadingType.setId(null);
        loadingType.setInsertedUserId(userId);
        loadingType.setInsertedDateTime(new Date());
        loadingTypeJPA.save(loadingType);
    }

    @Transactional
    public void update(LoadingType loadingType, Long userId) throws Exception {
        if (CommonUtils.isNull(loadingType.getId()))
            throw new RuntimeException("3005");
        if (CommonUtils.isNull(findOne(LoadingType.class, loadingType.getId())))
            throw new RuntimeException("3005");
        loadingType.setUpdatedUserId(userId);
        loadingType.setUpdatedDateTime(new Date());
        loadingTypeJPA.update(loadingType);
    }

    @Transactional
    public void delete(LoadingType loadingType) {
        loadingTypeJPA.remove(loadingType);
    }

    @Transactional
    public int delete(Long id) {
        Query query = entityManager.createQuery("delete from loadingType u where u.id=:id");
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return loadingTypeJPA.executeUpdate(query,param);
    }

    public LoadingType findOne(Class<LoadingType> aClass, Long id) {
        return loadingTypeJPA.findOne(aClass, id);
    }

    public List<LoadingType> findAll(Class<LoadingType> aClass) {
        return loadingTypeJPA.findAll(aClass);
    }


    public Page<LoadingType> findAll(Class<LoadingType> aClass, Integer page, Integer size) {
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return loadingTypeJPA.findAllWithPaging(aClass);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return loadingTypeJPA.findAllWithPaging(aClass, pageRequest);
    }


    public LoadingType findByCompanyAndCode(Long code, Long companyId) {
        String hql = "select o from loadingType o where o.companyId = :companyId and o.code = :code";
        Query query = entityManager.createQuery(hql);
        query.setParameter("companyId", companyId);
        query.setParameter("code", code);
        List<LoadingType> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
