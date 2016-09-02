package com.tp2assignment6.domaindesign.domain.services.employee.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;
import com.tp2assignment6.domaindesign.domain.repository.employee.ChauffeurRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.ChauffeurRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.services.employee.ChauffeurService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class ChauffeurServiceImpl extends IntentService implements ChauffeurService {
    private final ChauffeurRepository repo;

    private static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.ADD";
    private static final String ACTION_RESET = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.action.RESET";

    private static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.employee.Impl.extra.ADD";

    private static ChauffeurServiceImpl service = null;

    public static ChauffeurServiceImpl getInstance(){
        if (service == null)
            service = new ChauffeurServiceImpl();
        return service;
    }

    private ChauffeurServiceImpl(){
        super("ChauffeurServiceImpl");
        repo = new ChauffeurRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addChauffeur(Context context, Chauffeur chauffeur) {
        Intent intent = new Intent(context, ChauffeurServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, chauffeur);
        context.startService(intent);
    }

    @Override
    public void resetChauffeur(Context context) {
        Intent intent = new Intent(context, ChauffeurServiceImpl.class);
        intent.setAction(ACTION_RESET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final Chauffeur chauffeur = (Chauffeur) intent.getSerializableExtra(EXTRA_ADD);
                updateChauffeur(chauffeur);
            }
            else
                if (ACTION_RESET.equals(action)){
                    resetChauffeurRecord();
                }
        }
    }

    private void resetChauffeurRecord(){
        repo.deleteAll();
    }

    private void updateChauffeur(Chauffeur chauffeur){
        Chauffeur chauffeurRec = new Chauffeur.Builder()
                .idNumber(chauffeur.getIdNumber())
                .licenceNumber(chauffeur.getLicenceNumber())
                .build();
        Chauffeur updatedChauffeur = repo.update(chauffeurRec);
    }
}
