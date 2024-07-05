package org.vuelosglobales;

import org.vuelosglobales.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import org.vuelosglobales.manufacturer.adapter.out.ManufacturerMYSQLRepository;
import org.vuelosglobales.manufacturer.application.ManufacturerService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    
        final String url = "jdbc:mysql://localhost:3306/airportLetSee";
        final String user = "campus2023";
        final String password = "campus2023";

        ManufacturerMYSQLRepository mrepo = new ManufacturerMYSQLRepository(url, user, password);
        ManufacturerService mservice = new ManufacturerService(mrepo);
        ManufacturerConsoleAdapter mconsole = new ManufacturerConsoleAdapter(mservice);
        mconsole.menuManufacturer();  // This will open a console menu for managing manufacturers.
        


    }
}