package com.tp2assignment6.domaindesign.domain.services.employee;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.employee.SiteManager;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface SiteManagerService {
    void addSiteManager(Context context, SiteManager siteManager);
    void resetSiteManager(Context context);
}
