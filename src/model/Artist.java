/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hernandez.joseba
 */
public class Artist {
    private int artistid;
    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }
    
    

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  artistid + " | " + name ;
    }
    
    
}
