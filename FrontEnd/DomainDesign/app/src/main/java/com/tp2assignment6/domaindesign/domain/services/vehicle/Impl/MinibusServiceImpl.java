package com.tp2assignment6.domaindesign.domain.services.vehicle.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.Minibus;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.MinibusRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.MinibusRepository;
import com.tp2assignment6.domaindesign.domain.services.vehicle.MinibusService;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class MinibusServiceImpl extends IntentService implements MinibusService {
    private final MinibusRepository repo;

    private static MinibusServiceImpl service = null;

    public static MinibusServiceImpl getInstance(){
        if (service == null)
            service = new MinibusServiceImpl();
        return service;
    }

    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.extra.ADD";

    private MinibusServiceImpl(){
        super("MinibusServiceImpl");
        repo = new MinibusRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Minibus minibus = (Minibus) intent.getSerializableExtra(EXTRA_ADD);
        Minibus newMinibus = new Minibus.Builder()
                .idNumber(minibus.getIdNumber())
                .registrationNumber(minibus.getRegistrationNumber())
                .numberSeats(minibus.getNumberSeats())
                .build();
        repo.update(newMinibus);
    }

    @Override
    public void addMinibus(Context context, Minibus minibus) {
        Intent intent = new Intent(context, CoupeServiceImpl.class);
        intent.putExtra(EXTRA_ADD, minibus);
        context.startService(intent);
    }
}
