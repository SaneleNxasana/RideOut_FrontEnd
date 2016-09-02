package com.tp2assignment6.domaindesign.domain.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.employee.EmployeeContact;
import com.tp2assignment6.domaindesign.domain.repository.employee.EmployeeContactRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.EmployeeContactRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.services.employee.EmployeeContactService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class EmployeeContactServiceImpl extends IntentService implements EmployeeContactService {
    private final EmployeeContactRepository repo;

    public static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.ADD";
    public static final String ACTION_UPDATE = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.UPDATE";

    private static EmployeeContactServiceImpl service = null;

    public static EmployeeContactServiceImpl getInstance(){
        if (service == null)
            service = new EmployeeContactServiceImpl();
        return service;
    }

    private EmployeeContactServiceImpl(){
        super("EmployeeContactServiceImpl");
        repo = new EmployeeContactRepositoryImpl(App.getAppContext());
    }


    @Override
    public void addEmployeeContact(Context context, EmployeeContact contact) {
        Intent intent = new Intent(context, EmployeeContactServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);
    }

    @Override
    public void updateEmployeeContact(Context context, EmployeeContact contact) {
        Intent intent = new Intent(context, EmployeeContactServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final EmployeeContact address = (EmployeeContact) intent.getSerializableExtra(EXTRA_ADD);
            }
            else
                if (ACTION_UPDATE.equals(action)){
                    final EmployeeContact address = (EmployeeContact) intent.getSerializableExtra(EXTRA_UPDATE);
                }
        }
    }
}
