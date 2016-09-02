package com.tp2assignment6.domaindesign.domain.services.vehicle.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.SUV;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.SUVRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.SUVRepository;
import com.tp2assignment6.domaindesign.domain.services.vehicle.SUVService;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class SUVServiceImpl extends IntentService implements SUVService {
    private final SUVRepository repo;

    private static SUVServiceImpl service = null;

    public static SUVServiceImpl getInstance(){
        if (service == null)
            service = new SUVServiceImpl();
        return service;
    }

    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.extra.ADD";

    private SUVServiceImpl(){
        super("SUVServiceImpl");
        repo = new SUVRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SUV suv = (SUV) intent.getSerializableExtra(EXTRA_ADD);
        SUV newSUV = new SUV.Builder()
                .idNumber(suv.getIdNumber())
                .registrationNumber(suv.getRegistrationNumber())
                .numberSeats(suv.getNumberSeats())
                .build();
        repo.update(newSUV);
    }

    @Override
    public void addSUV(Context context, SUV suv) {
        Intent intent = new Intent(context, CoupeServiceImpl.class);
        intent.putExtra(EXTRA_ADD, suv);
        context.startService(intent);
    }
}
