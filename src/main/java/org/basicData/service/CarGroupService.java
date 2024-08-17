package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.dto.CarGroupDto;
import org.basicData.dto.PersonDto;
import org.basicData.model.CarGroup;
import org.basicData.repository.JPA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class CarGroupService {
    @PersistenceContext
    EntityManager entityManager;

    private final TransportServiceProxcy transportServiceProxcy;

    private final JPA<CarGroup, Long> carGroupJPA;

    public CarGroupService(TransportServiceProxcy transportServiceProxcy, JPA<CarGroup, Long> carGroupJPA) {
        this.transportServiceProxcy = transportServiceProxcy;
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
        String hql = "select o from carGroup o where o.companyId = :companyId and o.carType.id = :carTypeId and o.carCapacity.id=:carCapacityId";
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

    public Page<CarGroupDto> findAll(String token, String uuid, Integer page, Integer size) {
        List<CarGroup> carGroupList = new ArrayList<>();
        carGroupList = findAll(CarGroup.class);
        Page<PersonDto> personDtoPage = transportServiceProxcy.getPerson(token, uuid);
        List<PersonDto> personDtoList = personDtoPage.getContent();
        List<CarGroupDto> carGroupDtoList = new ArrayList<>();
        for (CarGroup carGroup : carGroupList) {
            Optional<PersonDto> personDto = personDtoList.stream().filter(a -> a.getId() == carGroup.getCompanyId()).findFirst();
            CarGroupDto carGroupDto = new CarGroupDto();
            carGroupDto.setId(carGroup.getId());
            carGroupDto.setCarCapacityId(carGroup.getCarCapacity().getId());
            carGroupDto.setCarCapacityName(carGroup.getCarCapacity().getName());
            carGroupDto.setCarTypeId(carGroup.getCarType().getId());
            carGroupDto.setCarTypeName(carGroup.getCarType().getName());
            carGroupDto.setCompanyId(carGroup.getCompanyId());
            carGroupDto.setCompanyName(personDto.get().getName());
            carGroupDto.setFactorValue(carGroup.getFactorValue());
            carGroupDtoList.add(carGroupDto);
        }
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return CommonUtils.listPaging(carGroupDtoList);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return CommonUtils.listPaging(carGroupDtoList, pageRequest);
    }

}
