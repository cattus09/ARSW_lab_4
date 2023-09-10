/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.springdemo.ui;

import edu.eci.arsw.springdemo.GrammarChecker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Obtiene una instancia de GrammarChecker del contexto de Spring
        GrammarChecker grammarChecker = applicationContext.getBean(GrammarChecker.class);

        // Realiza una verificación gramatical
        String textToCheck = "This is a sample text to check.";
        String result = grammarChecker.check(textToCheck);

        // Imprime el resultado
        System.out.println("Result: " + result);

       /*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        GrammarChecker gc = ac.getBean(GrammarChecker.class);
        System.out.println(gc.check("la la la "));*/
    }

}
