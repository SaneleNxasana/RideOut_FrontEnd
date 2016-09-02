package com.tp2assignment6.domaindesign.domain.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.employee.SiteManager;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.SiteManagerRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.employee.SiteManagerRepository;
import com.tp2assignment6.domaindesign.domain.services.employee.SiteManagerService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class SiteManagerServiceImpl extends IntentService implements SiteManagerService {
    private final SiteManagerRepository repo;

    private static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.ADD";
    private static final String ACTION_RESET = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.RESET";

    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.ADD";

    private static SiteManagerServiceImpl service = null;

    public static SiteManagerServiceImpl getInstance(){
        if (service == null)
            service = new SiteManagerServiceImpl();
        return service;
    }

    private SiteManagerServiceImpl(){
        super("SiteManagerServiceImpl");
        repo = new SiteManagerRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final SiteManager siteManager = (SiteManager) intent.getSerializableExtra(EXTRA_ADD);
                updateSiteManager(siteManager);
            }
            else
                if (ACTION_RESET.equals(action))
                    resetSiteManagerRecord();
        }
    }

    @Override
    public void addSiteManager(Context context, SiteManager siteManager) {
        Intent intent = new Intent(context, SiteManagerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, siteManager);
        context.startService(intent);
    }

    @Override
    public void resetSiteManager(Context context) {
        Intent intent = new Intent(context, SiteManagerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    private void resetSiteManagerRecord(){
        repo.deleteAll();
    }

    private void updateSiteManager(SiteManager siteManager){
        SiteManager siteMan = new SiteManager.Builder()
                .idNumber(siteManager.getIdNumber())
                .password(siteManager.getPassword())
                .build();
        SiteManager updatedSiteManager = repo.update(siteMan);
    }

}
