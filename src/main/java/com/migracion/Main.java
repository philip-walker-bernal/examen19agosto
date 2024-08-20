package main.java.com.migracion;

import main.java.com.migracion.controllers.*;
import main.java.com.migracion.models.*;
import main.java.com.migracion.utils.CSVReader;
import main.java.com.migracion.utils.ErrorLogger;
import main.java.com.migracion.dao.DatabaseConnection;

import java.text.SimpleDateFormat;
import java.util.*;




public class Main {
    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        ProvinciaController provinciaController = new ProvinciaController();
        OSexualController oSexualController = new OSexualController();
        APoliticaController aPoliticaController = new APoliticaController();
        NAcademicaController nAcademicaController = new NAcademicaController();
        PersonaController personaController = new PersonaController();

        Map<String, Integer> provinciaIds = new HashMap<>();
        Map<String, Integer> oSexualIds = new HashMap<>();
        Map<String, Integer> aPoliticaIds = new HashMap<>();
        Map<String, Integer> nAcademicaIds = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            DatabaseConnection.testConnection();

            List<String[]> records = csvReader.readCSV("src/main/resources/datos.csv");
            System.out.println("Total de registros leídos: " + records.size());

            int procesados = 0;
            int ignorados = 0;

            for (String[] record : records) {
                try {
                    String provinciaNombre = record[7];
                    int provinciaId;
                    if (!provinciaIds.containsKey(provinciaNombre)) {
                        Provincia provincia = new Provincia();
                        provincia.setNombre(provinciaNombre);
                        provinciaId = provinciaController.agregar(provincia);
                        provinciaIds.put(provinciaNombre, provinciaId);
                    } else {
                        provinciaId = provinciaIds.get(provinciaNombre);
                    }

                    String oSexualDesc = record[8];
                    int oSexualId;
                    if (!oSexualIds.containsKey(oSexualDesc)) {
                        OSexual oSexual = new OSexual();
                        oSexual.setDescripcion(oSexualDesc);
                        oSexualId = oSexualController.agregar(oSexual);
                        oSexualIds.put(oSexualDesc, oSexualId);
                    } else {
                        oSexualId = oSexualIds.get(oSexualDesc);
                    }

                    String aPoliticaNombre = record[9];
                    int aPoliticaId;
                    if (!aPoliticaIds.containsKey(aPoliticaNombre)) {
                        APolitica aPolitica = new APolitica();
                        aPolitica.setNombre(aPoliticaNombre);
                        aPoliticaId = aPoliticaController.agregar(aPolitica);
                        aPoliticaIds.put(aPoliticaNombre, aPoliticaId);
                    } else {
                        aPoliticaId = aPoliticaIds.get(aPoliticaNombre);
                    }

                    String nAcademicaDesc = record[10];
                    int nAcademicaId;
                    if (!nAcademicaIds.containsKey(nAcademicaDesc)) {
                        NAcademica nAcademica = new NAcademica();
                        nAcademica.setDescripcion(nAcademicaDesc);
                        nAcademicaId = nAcademicaController.agregar(nAcademica);
                        nAcademicaIds.put(nAcademicaDesc, nAcademicaId);
                    } else {
                        nAcademicaId = nAcademicaIds.get(nAcademicaDesc);
                    }

                    Persona persona = new Persona();
                    persona.setNombre(record[1]);
                    persona.setApell1(record[2]);
                    persona.setApell2(record[3]);
                    persona.setSexo(record[4].charAt(0));
                    persona.seteCivil(record[5].charAt(0));
                    persona.setNacido(dateFormat.parse(record[6]));
                    persona.setId_prov(provinciaId);
                    persona.setId_sexual(oSexualId);
                    persona.setId_poli(aPoliticaId);
                    persona.setId_acad(nAcademicaId);
                    persona.setSalario(Integer.parseInt(record[11]));
                    
                    personaController.agregar(persona);
                    procesados++;

                } catch (Exception e) {
                    ErrorLogger.logError("Error procesando registro: " + e.getMessage(), String.join(";", record));
                    ignorados++;
                }
            }

            System.out.println("Migración completada.");
            System.out.println("Registros procesados: " + procesados);
            System.out.println("Registros ignorados: " + ignorados);

        } catch (Exception e) {
            ErrorLogger.logError("Error general en la migración", e.getMessage());
            e.printStackTrace();
        }
    }
}