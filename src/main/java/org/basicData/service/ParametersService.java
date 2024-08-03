package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.model.Parameters;
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

@Service
@Slf4j
public class ParametersService {
    @PersistenceContext
    EntityManager entityManager;

    private final JPA<Parameters, Long> parametersJPA;

    public ParametersService(JPA<Parameters, Long> parametersJPA) {
        this.parametersJPA = parametersJPA;
    }

    @Value("${PageRequest.page}")
    private Integer page;
    @Value("${PageRequest.size}")
    private Integer size;


    @Transactional
    public void insert(Parameters parameters, Long userId) throws Exception {
        parameters.setId(null);
        parameters.setInsertedUserId(userId);
        parameters.setInsertedDateTime(new Date());
        parametersJPA.save(parameters);
    }

    @Transactional
    public void update(Parameters parameters, Long userId) throws Exception {
        if (CommonUtils.isNull(parameters.getId()))
            throw new RuntimeException("3005");
        if (CommonUtils.isNull(findOne(Parameters.class, parameters.getId())))
            throw new RuntimeException("3005");
        parameters.setUpdatedUserId(userId);
        parameters.setUpdatedDateTime(new Date());
        parametersJPA.update(parameters);
    }

    @Transactional
    public void delete(Parameters parameters) {
        parametersJPA.remove(parameters);
    }

    @Transactional
    public int delete(Long id) {
        Query query = entityManager.createQuery("delete from parameters p where p.id=:id");
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return parametersJPA.executeUpdate(query, param);
    }

    public Parameters findOne(Class<Parameters> aClass, Long id) {
        return parametersJPA.findOne(aClass, id);
    }

    public List<Parameters> findAll(Class<Parameters> aClass) {
        return parametersJPA.findAll(aClass);
    }

    public Page<Parameters> findAll(Class<Parameters> aClass, Integer page, Integer size) {
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return parametersJPA.findAllWithPaging(aClass);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return parametersJPA.findAllWithPaging(aClass, pageRequest);
    }


    public Parameters findByCompanyAndCode(String code, Long id) {
        String hql = "select o from parameters o where o.companyId = :id and o.paramCode = :code";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("code", code);
        List<Parameters> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
