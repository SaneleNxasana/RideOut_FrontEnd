package com.tp2assignment6.domaindesign.domain.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class ChaufferFactory {
    public static Chauffeur getChauffeur(String licence){
        return new Chauffeur.Builder()
                .licenceNumber(licence)
                .build();
    }
}
