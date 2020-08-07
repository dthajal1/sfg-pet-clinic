package com.clinic.sfgpetclinic.services.map;

import com.clinic.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(getNextId(), object);
        } else {
            throw new RuntimeException("Objects cannot be null");
        }
        return object;
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
//        or
//        for (Map.Entry<ID, T> entry : map.entrySet()) {
//            if (entry.getValue().equals(object)) {
//                map.remove(entry.getKey());
//            }
//        }
    }

    Long getNextId() {
        Long nextId;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException exp) {
            nextId = 1L;
        }
        return nextId;
    }

}
