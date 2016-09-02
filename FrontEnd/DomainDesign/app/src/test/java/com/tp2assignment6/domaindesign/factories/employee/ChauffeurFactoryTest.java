package com.tp2assignment6.domaindesign.factories.employee;

import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;
import com.tp2assignment6.domaindesign.domain.factories.employee.ChaufferFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by NXA-C.unltd on 2016/04/19.
 */
public class ChauffeurFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Chauffeur chauffeur = ChaufferFactory.getChauffeur("1003005002XY");
        Assert.assertEquals("1003005002XY", chauffeur.getLicenceNumber());
    }

    @Test
    public void testUpdate() throws Exception {
        Chauffeur chauffeur = ChaufferFactory.getChauffeur("1003005002XY");
        Chauffeur newChauffeur = new Chauffeur
                .Builder()
                .copy(chauffeur)
                .licenceNumber("2004006003AB")
                .build();
        Assert.assertEquals("2004006003AB", newChauffeur.getLicenceNumber());


    }
}
