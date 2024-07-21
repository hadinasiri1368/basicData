package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.model.CarGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarGroupService {
    @Autowired
    EntityManager entityManager;

    public CarGroup findByCompanyAndCode(Long carTypeId, Long carCapacityId, Long companyId) {
        String hql = "select o from carGroup o where o.companyId = :companyId and o.carTypeId = :carTypeId and o.carCapacityId=:carCapacityId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("companyId", companyId);
        query.setParameter("carTypeId", carTypeId);
        query.setParameter("carCapacityId", carCapacityId);
        List<CarGroup> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
}
