package com.tp2assignment6.domaindesign.domain.services.vehicle.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.vehicle.Sedan;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.Impl.SedanRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.vehicle.SedanRepository;
import com.tp2assignment6.domaindesign.domain.services.vehicle.SedanService;

/**
 * Created by NXA-C.unltd on 2016/05/08.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class SedanServiceImpl extends IntentService implements SedanService {
    private final SedanRepository repo;

    private static SedanServiceImpl service = null;

    public static SedanServiceImpl geetInstance(){
        if (service == null)
            service = new SedanServiceImpl();
        return service;
    }

    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.vehicle.Impl.extra.ADD";

    private SedanServiceImpl(){
        super("SedanServiceImpl");
        repo = new SedanRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Sedan sedan = (Sedan) intent.getSerializableExtra(EXTRA_ADD);
        Sedan newSedan = new Sedan.Builder()
                .idNumber(sedan.getIdNumber())
                .registrationNumber(sedan.getRegistrationNumber())
                .numberSeats(sedan.getNumberSeats())
                .build();
        repo.update(newSedan);
    }

    @Override
    public void addSedan(Context context, Sedan sedan) {
        Intent intent = new Intent(context, CoupeServiceImpl.class);
        intent.putExtra(EXTRA_ADD, sedan);
        context.startService(intent);
    }
}
