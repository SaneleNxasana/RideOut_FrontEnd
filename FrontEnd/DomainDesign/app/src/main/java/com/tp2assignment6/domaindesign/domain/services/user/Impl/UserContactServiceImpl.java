package com.tp2assignment6.domaindesign.domain.services.user.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.tp2assignment6.domaindesign.domain.conf.util.App;
import com.tp2assignment6.domaindesign.domain.domain.user.UserContact;
import com.tp2assignment6.domaindesign.domain.repository.user.Impl.UserContactRepositoryImpl;
import com.tp2assignment6.domaindesign.domain.repository.user.UserContactRepository;
import com.tp2assignment6.domaindesign.domain.services.user.UserContactService;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */

//THIS IS AN INTENT SERVICE.
//This service is not bound to any activity.

public class UserContactServiceImpl extends IntentService implements UserContactService {
    private final UserContactRepository repo;

    public static final String ACTION_ADD = "com.tp2assignment6.domaindesign.domain.services.user.Impl.action.ADD";
    public static final String ACTION_UPDATE = "com.tp2assignment6.domaindesign.domain.services.user.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "com.tp2assignment6.domaindesign.domain.services.user.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.tp2assignment6.domaindesign.domain.services.user.Impl.extra.UPDATE";

    private static UserContactServiceImpl service = null;

    public static UserContactServiceImpl getInstance(){
        if (service == null)
            service = new UserContactServiceImpl();
        return service;
    }

    private UserContactServiceImpl(){
        super("UserContactServiceImpl");
        repo = new UserContactRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)){
                final UserContact userContact = (UserContact) intent.getSerializableExtra(EXTRA_ADD);
            }
            else
                if (ACTION_UPDATE.equals(action)){
                    final UserContact userContact = (UserContact) intent.getSerializableExtra(EXTRA_UPDATE);
                }
        }
    }

    @Override
    public void addUserContact(Context context, UserContact contact) {
        Intent intent = new Intent(context, UserContactServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);
    }

    @Override
    public void updateUserContact(Context context, UserContact contact) {
        Intent intent = new Intent(context, UserContactServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);
    }
}
