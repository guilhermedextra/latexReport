package br.com.dextra.latexReport;

import de.nixosoft.jlr.JLRGenerator;
import de.nixosoft.jlr.JLRConverter;
import de.nixosoft.jlr.JLROpener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        File workingDirectory = new File("/home/guilherme.vicente" + File.separator + "invoices");

        File template = new File(workingDirectory.getAbsolutePath() + File.separator + "invoiceTemplate.tex");

        File tempDir = new File(workingDirectory.getAbsolutePath() + File.separator + "temp");

        if (!tempDir.isDirectory()) {
            tempDir.mkdir();
        }

        File invoice1 = new File(tempDir.getAbsolutePath() + File.separator + "invoice1.tex");
        File invoice2 = new File(tempDir.getAbsolutePath() + File.separator + "invoice2.tex");

         try {
            JLRConverter converter = new JLRConverter(workingDirectory);

            converter.replace("Number", "1");
            converter.replace("CustomerName", "Ivan Pfeiffer");
            converter.replace("CustomerStreet", "Schwarzer Weg 4");
            converter.replace("CustomerZip", "13505 Berlin");

            ArrayList<ArrayList<String>> services = new ArrayList<ArrayList<String>>();

            ArrayList<String> subservice1 = new ArrayList<String>();
            ArrayList<String> subservice2 = new ArrayList<String>();
            ArrayList<String> subservice3 = new ArrayList<String>();

            subservice1.add("Software");
            subservice1.add("50");
            subservice2.add("Hardware1");
            subservice2.add("500");
            subservice3.add("Hardware2");
            subservice3.add("850");

            services.add(subservice1);
            services.add(subservice2);
            services.add(subservice3);

            converter.replace("services", services);


            if (!converter.parse(template, invoice1)) {
                System.out.println(converter.getErrorMessage());
            }

            converter.replace("Number", "2");
            converter.replace("CustomerName", "Mike Mueller");
            converter.replace("CustomerStreet", "Prenzlauer Berg 12");
            converter.replace("CustomerZip", "10405 Berlin");

            services = new ArrayList<ArrayList<String>>();

            subservice1 = new ArrayList<String>();
            subservice2 = new ArrayList<String>();
            subservice3 = new ArrayList<String>();

            subservice1.add("Software");
            subservice1.add("150");
            subservice2.add("Hardware");
            subservice2.add("500");
            subservice3.add("Test");
            subservice3.add("350");

            services.add(subservice1);
            services.add(subservice2);
            services.add(subservice3);

            converter.replace("services", services);


            if (!converter.parse(template, invoice2)) {
                System.out.println(converter.getErrorMessage());
            }


            File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");

            JLRGenerator pdfGen = new JLRGenerator();

            if (!pdfGen.generate(invoice1, desktop, workingDirectory)) {
                System.out.println(pdfGen.getErrorMessage());
            }

            JLROpener.open(pdfGen.getPDF());

            if (!pdfGen.generate(invoice2, desktop, workingDirectory)) {
                System.out.println(pdfGen.getErrorMessage());
            }

            //JLROpener.open(pdfGen.getPDF());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}