package com.tp2assignment6.domaindesign.repository.employee;

import android.test.AndroidTestCase;

import com.tp2assignment6.domaindesign.domain.domain.employee.Chauffeur;
import com.tp2assignment6.domaindesign.domain.repository.employee.ChauffeurRepository;
import com.tp2assignment6.domaindesign.domain.repository.employee.Impl.ChauffeurRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by NXA-C.unltd on 2016/04/25.
 */
public class ChauffeurRepositoryTest extends AndroidTestCase {
    private static final String TAG = "CHAUFFEUR TEST";
    private Long idNumber;

    public void testDatabasesFunctions() throws Exception{
        ChauffeurRepository repo = new ChauffeurRepositoryImpl(this.getContext());

        //CREATE
        Chauffeur createEntity = new Chauffeur.Builder()
                .licenceNumber("1002003002XY")
                .build();
        Chauffeur insertEntity = repo.create(createEntity);
        idNumber = insertEntity.getIdNumber();
        Assert.assertNotNull(TAG + " CREATE", insertEntity);

        //READ ALL
        Set<Chauffeur> chauffeurs = repo.readAll();
        Assert.assertTrue(TAG + " READ ALL", chauffeurs.size() > 0);

        //READ ITEM
        Chauffeur item = repo.readById(idNumber);
        Assert.assertNotNull(TAG + " READ ITEM", item);

        //UPDATE ITEM
        Chauffeur updateItem = new Chauffeur.Builder()
                .copy(item)
                .licenceNumber("3006009001AB")
                .build();
        repo.update(updateItem);
        Chauffeur newItem = repo.readById(idNumber);
        Assert.assertEquals(TAG + " UPDATE ITEM", "3006009001AB", newItem.getLicenceNumber());

        //DELETE ITEM
        repo.delete(updateItem);
        Chauffeur deleteItem = repo.readById(idNumber);
        Assert.assertNull(TAG + " DELETE", deleteItem);
    }
}
