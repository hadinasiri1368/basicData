package org.basicData.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.dto.LoadingTypeCompanyDto;
import org.basicData.dto.PersonDto;
import org.basicData.model.LoadingType;
import org.basicData.model.LoadingTypeCompany;
import org.basicData.repository.JPA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class LoadingTypeCompanyService {
    @PersistenceContext
    EntityManager entityManager;

    private final JPA<LoadingTypeCompany, Long> loadingTypeCompanyJPA;
    private final TransportServiceProxcy transportServiceProxcy;
    private final LoadingTypeService loadingTypeService;

    @Value("${PageRequest.page}")
    private Integer page;
    @Value("${PageRequest.size}")
    private Integer size;

    public LoadingTypeCompanyService(JPA<LoadingTypeCompany, Long> loadingTypeCompanyJPA, TransportServiceProxcy transportServiceProxcy, LoadingTypeService loadingTypeService) {
        this.loadingTypeCompanyJPA = loadingTypeCompanyJPA;
        this.transportServiceProxcy = transportServiceProxcy;
        this.loadingTypeService = loadingTypeService;
    }


    @Transactional
    public void insert(LoadingTypeCompany loadingTypeCompany, Long userId) throws Exception {
        loadingTypeCompany.setId(null);
        loadingTypeCompany.setInsertedUserId(userId);
        loadingTypeCompany.setInsertedDateTime(new Date());
        loadingTypeCompanyJPA.save(loadingTypeCompany);
    }

    @Transactional
    public void update(LoadingTypeCompany loadingTypeCompany, Long userId) throws Exception {
        if (CommonUtils.isNull(loadingTypeCompany.getId()))
            throw new RuntimeException("3005");
        if (CommonUtils.isNull(findOne(LoadingTypeCompany.class, loadingTypeCompany.getId())))
            throw new RuntimeException("3005");
        loadingTypeCompany.setUpdatedUserId(userId);
        loadingTypeCompany.setUpdatedDateTime(new Date());
        loadingTypeCompanyJPA.update(loadingTypeCompany);
    }

    @Transactional
    public void delete(LoadingTypeCompany loadingTypeCompany) {
        loadingTypeCompanyJPA.remove(loadingTypeCompany);
    }

    @Transactional
    public int delete(Long id) {
        Query query = entityManager.createQuery("delete from loadingTypeCompany u where u.id=:id");
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return loadingTypeCompanyJPA.executeUpdate(query, param);
    }

    public LoadingTypeCompany findOne(Class<LoadingTypeCompany> aClass, Long id) {
        return loadingTypeCompanyJPA.findOne(aClass, id);
    }

    public List<LoadingTypeCompany> findAll(Class<LoadingTypeCompany> aClass) {
        return loadingTypeCompanyJPA.findAll(aClass);
    }


    public Page<LoadingTypeCompany> findAll(Class<LoadingTypeCompany> aClass, Integer page, Integer size) {
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return loadingTypeCompanyJPA.findAllWithPaging(aClass);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return loadingTypeCompanyJPA.findAllWithPaging(aClass, pageRequest);
    }

    public LoadingTypeCompany findByCompanyAndCode(Long loadingTypeId, Long companyId) {
        String hql = "select o from loadingTypeCompany o where o.companyId = :companyId and o.loadingType.id = :loadingTypeId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("companyId", companyId);
        query.setParameter("loadingTypeId", loadingTypeId);
        List<LoadingTypeCompany> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    public Page<LoadingTypeCompanyDto> findAll(String token, String uuid, Integer page, Integer size) {
        List<LoadingTypeCompany> loadingTypeCompanyList = new ArrayList<>();
        loadingTypeCompanyList = findAll(LoadingTypeCompany.class);
        List<LoadingType> loadingTypeList = loadingTypeService.findAll(LoadingType.class);
        Page<PersonDto> personDtoPage = transportServiceProxcy.getPerson(token, uuid);
        List<PersonDto> personDtoList = personDtoPage.getContent();
        List<LoadingTypeCompanyDto> loadingTypeDtos = new ArrayList<>();
        for (LoadingTypeCompany loadingTypeCompany : loadingTypeCompanyList) {
            Optional<PersonDto> personDto = personDtoList.stream().filter(a -> a.getId().equals(loadingTypeCompany.getCompanyId())).findFirst();
            Optional<LoadingType> loadingType = loadingTypeList.stream().filter(a->a.getId().equals(loadingTypeCompany.getLoadingType().getId())).findFirst();
            LoadingTypeCompanyDto loadingTypeCompanyDto = new LoadingTypeCompanyDto();
            loadingTypeCompanyDto.setId(loadingTypeCompany.getId());
            loadingTypeCompanyDto.setCode(loadingType.get().getCode());
            loadingTypeCompanyDto.setName(loadingType.get().getName());
            loadingTypeCompanyDto.setCompanyId(loadingTypeCompany.getCompanyId());
            loadingTypeCompanyDto.setCompanyName(personDto.get().getName());
            loadingTypeCompanyDto.setFactorValue(loadingTypeCompany.getFactorValue());
            loadingTypeDtos.add(loadingTypeCompanyDto);
        }
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return CommonUtils.listPaging(loadingTypeDtos);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return CommonUtils.listPaging(loadingTypeDtos, pageRequest);
    }

}
