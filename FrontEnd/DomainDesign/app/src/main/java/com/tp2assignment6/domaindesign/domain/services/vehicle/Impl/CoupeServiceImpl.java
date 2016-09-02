package com.tp2assignment6.domaindesign.domain.services.vehicle.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.Coupe;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.CoupeRepository;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.CoupeRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.services.vehicle.CoupeService;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class CoupeServiceImpl extends IntentService implements CoupeService {
    private final CoupeRepository repo;

    public static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.extra.ADD";

    private static CoupeServiceImpl service = null;

    public static CoupeServiceImpl getInstance(){
        if (service == null)
            service = new CoupeServiceImpl();
        return service;
    }

    private CoupeServiceImpl(){
        super("CoupeServiceImpl");
        repo = new CoupeRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addCoupe(Context context, Coupe coupe) {
        Intent intent = new Intent(context, CoupeServiceImpl.class);
        intent.putExtra(EXTRA_ADD, coupe);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Coupe coupe = (Coupe) intent.getSerializableExtra(EXTRA_ADD);
        Coupe newCoupe = new Coupe.Builder()
                .idNumber(coupe.getIdNumber())
                .registrationNumber(coupe.getRegistrationNumber())
                .numberSeats(coupe.getNumberSeats())
                .build();
        repo.update(newCoupe);
    }
}
