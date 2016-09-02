package com.tp2assignment6.domaindesign.domain.services.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.System;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */
public interface SystemService {
    System readById(Long id);
    System update(System entity);
    Set<System> readAll();
}
