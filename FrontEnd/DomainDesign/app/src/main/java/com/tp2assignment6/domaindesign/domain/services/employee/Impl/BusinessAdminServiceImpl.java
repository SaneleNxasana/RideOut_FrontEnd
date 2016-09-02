package com.tp2assignment6.domaindesign.domain.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.employee.BusinessAdmin;
import com.tp2assignment6.domaindesign.domain.repository.employee.BusinessAdminRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.BusinessAdminRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.services.employee.BusinessAdminService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class BusinessAdminServiceImpl extends IntentService implements BusinessAdminService {
    private final BusinessAdminRepository repo;

    private static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.ADD";
    private static final String ACTION_RESET = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.RESET";
    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.ADD";

    private static BusinessAdminServiceImpl service = null;

    private static BusinessAdminServiceImpl getInstance(){
        if (service == null)
            service = new BusinessAdminServiceImpl();
        return service;
    }

    private BusinessAdminServiceImpl(){
        super("BusinessAdminServiceImpl");
        repo = new BusinessAdminRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addBusinessAdmin(Context context, BusinessAdmin businessAdmin) {
        Intent intent = new Intent(context, BusinessAdminServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, businessAdmin);
        context.startService(intent);
    }

    @Override
    public void resetBusinessAdmin(Context context) {
        Intent intent = new Intent(context, BusinessAdminServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final BusinessAdmin businessAdmin = (BusinessAdmin) intent.getSerializableExtra(EXTRA_ADD);
                updateBusinessAdmin(businessAdmin);
            }
            else
                if (ACTION_RESET.equals(action)){
                    resetBusinessAdminRecord();
                }
        }
    }

    private void resetBusinessAdminRecord(){
        repo.deleteAll();
    }

    private void updateBusinessAdmin(BusinessAdmin businessAdmin){
        BusinessAdmin admin = new BusinessAdmin.Builder()
                .idNumber(businessAdmin.getIdNumber())
                .password(businessAdmin.getPassword())
                .build();
        BusinessAdmin updatedBusinessAdmin = repo.update(admin);
    }
}
