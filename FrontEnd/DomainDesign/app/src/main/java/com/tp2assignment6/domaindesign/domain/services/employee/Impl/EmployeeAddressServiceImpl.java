package com.tp2assignment6.domaindesign.domain.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeAddress;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeAddressRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.EmployeeAddressRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.services.employee.EmployeeAddressService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class EmployeeAddressServiceImpl extends IntentService implements EmployeeAddressService {
    private final EmployeeAddressRepository repo;

    public static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.ADD";
    public static final String ACTION_UPDATE = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.UPDATE";
    public static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.UPDATE";

    private static EmployeeAddressServiceImpl service = null;

    public static EmployeeAddressServiceImpl getInstance(){
        if (service == null)
            service = new EmployeeAddressServiceImpl();
        return service;
    }

    private EmployeeAddressServiceImpl(){
        super("EmployeeAddressServiceImpl");
        repo = new EmployeeAddressRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addEmployeeAddress(Context context, EmployeeAddress address) {
        Intent intent = new Intent(context, EmployeeAddressServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }

    @Override
    public void updateEmployeeAddress(Context context, EmployeeAddress address) {
        Intent intent = new Intent(context, EmployeeAddressServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final EmployeeAddress address = (EmployeeAddress) intent.getSerializableExtra(EXTRA_ADD);
            }
            else
                if (ACTION_UPDATE.equals(action)){
                    final EmployeeAddress address = (EmployeeAddress) intent.getSerializableExtra(EXTRA_UPDATE);
                }
        }
    }
}
