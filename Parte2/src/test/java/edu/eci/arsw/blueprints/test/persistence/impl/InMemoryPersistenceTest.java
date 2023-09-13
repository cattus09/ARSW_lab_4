/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
    } 

    @Test
    public void getNonExistentBlueprintTest() throws BlueprintNotFoundException {
        InMemoryBlueprintPersistence persistence = new InMemoryBlueprintPersistence();
        persistence.getBlueprint("nonexistent", "bpname");
    }

    @Test
    public void shouldFilterByRedundant() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio", pts0);

        bpServices.addNewBlueprint(bp0);
        bp0 = bpServices.filterBluePrint(bp0);

        Point[] ptsCorrects = new Point[]{new Point(25, 10), new Point(15, 15), new Point(10, 5)};
        List<Point> ptsReal= bp0.getPoints();
        try{
            for (int i = 0; i < ptsCorrects.length; i++){
                assertEquals(ptsCorrects[i].getX(),ptsReal.get(i).getX());
                assertEquals(ptsCorrects[i].getY(),ptsReal.get(i).getY());
            }
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
    

    //para que de correcto cambie el @Service de RedundancyFiltering a UndersamplingFiltering
    @Test
    public void shouldUndersamplingFiltering() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(30, 45), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio", pts0);

        bpServices.addNewBlueprint(bp0);
        bp0 = bpServices.filterBluePrint(bp0);

        Point[] ptsCorrects = new Point[]{new Point(25, 10), new Point(30, 45)};
        List<Point> ptsReal= bp0.getPoints();
        try{
            for (int i = 0; i < ptsCorrects.length; i++){
                assertEquals(ptsCorrects[i].getX(),ptsReal.get(i).getX());
                assertEquals(ptsCorrects[i].getY(),ptsReal.get(i).getY());
            }
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
