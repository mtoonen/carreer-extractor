/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.carreer.extractor;

import java.util.List;

/**
 *
 * @author Meine Toonen
 */
public class Carreer {
    private String name;
    private int page;
    private String[] exits;
    private String[] entries;
    
    public Carreer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String[] getExits() {
        return exits;
    }

    public void setExits(String[] exits) {
        this.exits = exits;
    }

    public String[] getEntries() {
        return entries;
    }

    public void setEntries(String[] entries) {
        this.entries = entries;
    }
    
}
