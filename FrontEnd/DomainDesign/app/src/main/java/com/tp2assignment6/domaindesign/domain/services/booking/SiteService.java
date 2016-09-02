package com.tp2assignment6.domaindesign.domain.services.booking;

import com.tp2assignment6.domaindesign.domain.domain.booking.Site;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */
public interface SiteService {
    Site readById(Long id);
    Site update(Site entity);
    Set<Site> readAll();
}
