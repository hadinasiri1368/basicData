package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.model.LoadingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoadingTypeService {
    @Autowired
    EntityManager entityManager;

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
