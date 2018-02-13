/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.carreer.extractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Meine Toonen
 */
public class Extractor {

    public static void main(String[] args) {
        Extractor e = new Extractor();
        e.extract("/home/meine/dev/carreer-extractor/Career_Compendium.pdf");
        //http://visjs.org/
    }

    public Extractor() {

    }

    public void extract(String f) {
        try {
            File file = new File(f);
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();

            Splitter splitter = new Splitter();

            List<PDDocument> pages = splitter.split(document);
            int i = 0;
            int startingpage = 7;
            List<Carreer> carreers = new ArrayList<>();
            for (PDDocument doc : pages) {
                i++;
                if(i <= 7){
                    continue;
                }
                pdfStripper.setSortByPosition(true);
                String text = pdfStripper.getText(doc);
                carreers.add(parsePage(text, i));
                
            }
            document.close();
            Map<String, Carreer> mapping = new HashMap<>();
            for (Carreer carreer : carreers) {
                mapping.put(carreer.getName(), carreer);
            }
        } catch (IOException ex) {
            Logger.getLogger(Extractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Carreer parsePage(String text, int page){
        Carreer c = new Carreer();
        String[] parts = text.split("\n");
        int index = - 1;
        String[] carreerExits = null;
        String[] carreerEntries = null;
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            if(part.contains("Exits")){
                carreerExits = part.substring(part.indexOf(":") +1).split(", ");
                c.setExits(carreerExits);
            }
            if(part.contains("Entries")){
                carreerEntries = part.substring(part.indexOf(":") +1).split(", ");
                c.setEntries(carreerEntries);
            }
        }
        String name = null;
        if(parts.length > 0){
            name = parts[1];
            c.setName(name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
        }
        
        return c;
    }
}
