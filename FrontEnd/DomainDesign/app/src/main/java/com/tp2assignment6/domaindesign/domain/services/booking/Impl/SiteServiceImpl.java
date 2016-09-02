package com.tp2assignment6.domaindesign.domain.services.booking.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.Site;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.SiteRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.SiteRepository;
import com.tp2assignment6.domaindesign.domain.services.booking.SiteService;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS A BOUND SERVICE.
//This service is bound to another activity to function fully.

public class SiteServiceImpl extends Service implements SiteService {
    private final SiteRepository siteRepository;
    private final IBinder localBinder = new SiteServiceLocalBinder();
    private static SiteServiceImpl service = null;

    public static SiteServiceImpl getInstance(){
        if (service == null)
            service = new SiteServiceImpl();
        return service;
    }

    public SiteServiceImpl(){
        siteRepository = new SiteRepositoryImpl(App.getAppContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Site readById(Long id) {
        return siteRepository.readById(id);
    }

    @Override
    public Site update(Site entity) {
        return siteRepository.update(entity);
    }

    @Override
    public Set<Site> readAll() {
        return siteRepository.readAll();
    }

    public class SiteServiceLocalBinder extends Binder {
        public SiteServiceImpl getService(){
            return SiteServiceImpl.this;
        }
    }


}
