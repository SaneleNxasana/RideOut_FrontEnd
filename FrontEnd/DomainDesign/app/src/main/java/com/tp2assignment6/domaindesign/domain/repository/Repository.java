package com.tp2assignment6.domaindesign.domain.repository;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/21.
 */
public interface Repository<E, ID> {
    E create(ID id);
    E readById (Long id);
    Set<E> readAll ();
    E update (ID id);
    E delete (E entity);
    int deleteAll();
}
