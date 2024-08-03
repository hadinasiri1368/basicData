package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.model.CarGroup;
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
public class CarGroupService {
    @PersistenceContext
    EntityManager entityManager;

    private final JPA<CarGroup, Long> carGroupJPA;

    public CarGroupService(JPA<CarGroup, Long> carGroupJPA) {
        this.carGroupJPA = carGroupJPA;
    }

    @Value("${PageRequest.page}")
    private Integer page;
    @Value("${PageRequest.size}")
    private Integer size;


    @Transactional
    public void insert(CarGroup carGroup, Long userId) throws Exception {
        carGroup.setId(null);
        carGroup.setInsertedUserId(userId);
        carGroup.setInsertedDateTime(new Date());
        carGroupJPA.save(carGroup);
    }

    @Transactional
    public void update(CarGroup carGroup, Long userId) throws Exception {
        if (CommonUtils.isNull(carGroup.getId()))
            throw new RuntimeException("3005");
        if (CommonUtils.isNull(findOne(CarGroup.class, carGroup.getId())))
            throw new RuntimeException("3005");
        carGroup.setUpdatedUserId(userId);
        carGroup.setUpdatedDateTime(new Date());
        carGroupJPA.update(carGroup);
    }

    @Transactional
    public void delete(CarGroup carGroup) {
        carGroupJPA.remove(carGroup);
    }

    @Transactional
    public int delete(Long id) {
        Query query = entityManager.createQuery("delete from carGroup c where c.id=:id");
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return carGroupJPA.executeUpdate(query, param);
    }

    public CarGroup findOne(Class<CarGroup> aClass, Long id) {
        return carGroupJPA.findOne(aClass, id);
    }

    public List<CarGroup> findAll(Class<CarGroup> aClass) {
        return carGroupJPA.findAll(aClass);
    }

    public Page<CarGroup> findAll(Class<CarGroup> aClass, Integer page, Integer size) {
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return carGroupJPA.findAllWithPaging(aClass);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return carGroupJPA.findAllWithPaging(aClass, pageRequest);
    }

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
