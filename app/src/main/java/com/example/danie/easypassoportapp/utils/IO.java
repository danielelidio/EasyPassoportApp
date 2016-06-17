package com.example.danie.easypassoportapp.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author daniel.elidio
 */
public class IO {

    private File file = null;
    
    private String[] allLines;

    public IO(String file) {
        this.file = new File(file);
        this.readAllLines();
    }
    
    public IO(String file, boolean relative) {
        if(relative) {
            this.file = new File(new File("").getAbsoluteFile() + "/" + file);
        }
        else {
            this.file = new File(file);
        }
        this.readAllLines();
    }
    
    public void readAllLines() {
        int nrOfLines = (int) this.getNumberOfLines();
        this.allLines = new String[nrOfLines];
        
        BufferedReader br = null;
        String line = null;
        try {
            
            br = new BufferedReader(new FileReader(this.file));
            int cont = 1;
            while (cont <= nrOfLines) {
                this.allLines[cont - 1] = br.readLine();
                cont++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getLine(long i) throws IOException, Exception {
        BufferedReader br = null;
        String line = null;
        line = this.allLines[(int)i - 1];
        
        return line;
    }
    
    public long getNumberOfLines() {
        BufferedReader br = null;
        String line = null;
        int cont = 0;
        try {
            br = new BufferedReader(new FileReader(this.file));
            while ((line = br.readLine()) != null) {
                cont++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return cont;
    }
}
