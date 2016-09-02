package com.tp2assignment6.domaindesign.domain.services.booking.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.booking.System;
import com.tp2assignment6.domaindesign.domain.repository.booking.Impl.SystemRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.booking.SystemRepository;
import com.tp2assignment6.domaindesign.domain.services.booking.SystemService;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS A BOUND SERVICE.
//This service is bound to another activity to function fully.

public class SystemServiceImpl extends Service implements SystemService {
    private final SystemRepository systemRepository;
    private final IBinder localBinder = new SystemServiceLocalBinder();
    private static SystemServiceImpl service = null;

    public static SystemServiceImpl getInstance(){
        if (service == null)
            service = new SystemServiceImpl();
        return service;
    }

    public SystemServiceImpl(){
        systemRepository = new SystemRepositoryImpl(App.getAppContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public System readById(Long id) {
        return systemRepository.readById(id);
    }

    @Override
    public System update(System entity) {
        return systemRepository.update(entity);
    }

    @Override
    public Set<System> readAll() {
        return systemRepository.readAll();
    }

    public class SystemServiceLocalBinder extends Binder{
        public SystemServiceImpl getService(){
            return SystemServiceImpl.this;
        }
    }


}
