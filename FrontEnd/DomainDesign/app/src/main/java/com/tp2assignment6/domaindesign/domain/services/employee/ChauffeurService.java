package com.tp2assignment6.domaindesign.domain.services.employee;

import android.content.Context;

import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;

/**
 * Created by NXA-C.unltd on 2016/05/07.
 */
public interface ChauffeurService {
    void addChauffeur(Context context, Chauffeur chauffeur);
    void resetChauffeur(Context context);
}
