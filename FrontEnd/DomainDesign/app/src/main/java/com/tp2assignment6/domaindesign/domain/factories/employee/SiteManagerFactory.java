package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.SiteManager;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class SiteManagerFactory {
    public static SiteManager getSiteManger(String pass){
        return new SiteManager.Builder()
                .password(pass)
                .build();
    }
}
