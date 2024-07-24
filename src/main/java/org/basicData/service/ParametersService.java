package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ParametersService {
    @PersistenceContext
    EntityManager entityManager;
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
