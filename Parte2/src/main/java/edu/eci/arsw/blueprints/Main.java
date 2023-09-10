package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String a[]) throws BlueprintNotFoundException, BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);

        Point[] pts0 = new Point[]{new Point(25, 15),new Point(15, 15)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio",pts0);

        Blueprint bp1 = new Blueprint("Nicolas","Casa");

        bpServices.addNewBlueprint(bp0);
        bpServices.addNewBlueprint(bp1);   
        //redundancyFiltering();
        undersamplingFiltering();
    }
    
    public static void redundancyFiltering() throws BlueprintPersistenceException, BlueprintNotFoundException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio",pts0);
        System.out.println("Antes de usar el filtro: " + bp0.toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
        System.out.println();
        bpServices.addNewBlueprint(bp0);
        System.out.println("Después de usar el filtro: " + bpServices.getBlueprint("Sergio", "Edificio").toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
    }
    
    public static void undersamplingFiltering() throws BlueprintNotFoundException, BlueprintPersistenceException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        Point[] pts0 = new Point[]{new Point(25, 10), new Point(15, 15), new Point(15, 15), new Point(10, 5)};
        Blueprint bp0 = new Blueprint("Sergio", "Edificio",pts0);
        System.out.println("Antes de usar el filtro: " + bp0.toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
        System.out.println();
        bpServices.addNewBlueprint(bp0);
        System.out.println("Después de usar el filtro: " + bpServices.getBlueprint("Sergio", "Edificio").toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
    }
    
}