package com.tp2assignment6.domaindesign.domain.services.employee;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.employee.BusinessAdmin;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface BusinessAdminService {
    void addBusinessAdmin(Context context, BusinessAdmin businessAdmin);
    void resetBusinessAdmin(Context context);
}
