package org.basicData.service;

import lombok.extern.slf4j.Slf4j;
import org.basicData.common.CommonUtils;
import org.basicData.model.BaseEntity;
import org.basicData.repository.JPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GenericService<Entity> {
    @Autowired
    private JPA<Entity, Long> genericJPA;

    @Value("${PageRequest.page}")
    private Integer page;
    @Value("${PageRequest.size}")
    private Integer size;

    @Transactional
    public void insert(Entity entity, Long userId) throws Exception {
        Method m = entity.getClass().getMethod("setId", Long.class);
        m.invoke(entity, (Long) null);
        ((BaseEntity) entity).setInsertedUserId(userId);
        ((BaseEntity) entity).setInsertedDateTime(new Date());
        genericJPA.save(entity);
    }

    @Transactional
    public void update(Entity entity, Long userId, Class<Entity> aClass) throws Exception {
        Method m = entity.getClass().getMethod("getId");
        Long id = (Long) m.invoke(entity);
        if (CommonUtils.isNull(id))
            throw new RuntimeException("3005");
        if (CommonUtils.isNull(findOne(aClass, id)))
            throw new RuntimeException("3005");
        ((BaseEntity) entity).setUpdatedUserId(userId);
        ((BaseEntity) entity).setUpdatedDateTime(new Date());
        genericJPA.update(entity);
    }

    @Transactional
    public void delete(Entity entity) {
        genericJPA.remove(entity);
    }

    @Transactional
    public int delete(Long id, Class<Entity> aClass) {
        int returnValue = genericJPA.remove(id, aClass);
        if (returnValue == 0) {
            throw new RuntimeException("3005");
        }
        return returnValue;
    }

    public Entity findOne(Class<Entity> aClass, Long id) {
        return genericJPA.findOne(aClass, id);
    }

    public List<Entity> findAll(Class<Entity> aClass) {
        return genericJPA.findAll(aClass);
    }

    public Page<Entity> findAll(Class<Entity> aClass, Integer page, Integer size) {
        if (CommonUtils.isNull(page) && CommonUtils.isNull(size)) {
            return genericJPA.findAllWithPaging(aClass);
        }
        PageRequest pageRequest = PageRequest.of(CommonUtils.isNull(page, this.page), CommonUtils.isNull(size, this.size));
        return genericJPA.findAllWithPaging(aClass, pageRequest);

    }


}
