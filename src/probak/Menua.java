/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probak;

import java.util.Scanner;
import model.Artist;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import model.Metodoak;

/**
 *
 * @author hernandez.joseba
 */
public class Menua {

    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int zbki = 234234, id;
        String izena;
        System.out.println("======================================================");
        System.out.println("Menua");
        while (zbki != 0) {
            System.out.println("======================================================");
            System.out.println("\t1-Artista guztiak bistaratu");
            System.out.println("\t2-Artista berri bat sartu");
            System.out.println("\t3-Artista bat ezabatu");
            System.out.println("\t4-Artista bat Eguneratu");
            System.out.println("\t5-Artistak alfabetikoki");
            System.out.println("\t6-Artista bakarra erakutsi");
            System.out.println("\t7-Hasierako letra berdinarekin dauden artistak");
            System.out.println("\t8-Artistak dituen diskak ikusi");
            System.out.println("\t9-Generoak zutabeetan ikusi");
            System.out.println("\t10-Abestiak generoaren arabera ordenatuta");
            System.out.println("\t0-Irten");
            System.out.println("======================================================");
            System.out.print("Zure aukera: ");
            zbki = in.nextInt();
            switch (zbki) {
                case 0:
                    break;
                case 1:
                    Metodoak.datuakIkusi();
                    break;
                case 2:
                    System.out.print("Sartu artistaren izena: ");
                    izena = in.next();
                    Metodoak.datuaGorde(new Artist(izena));
                    break;
                case 3:
                    System.out.print("Sartu artistaren id-a: ");
                    id = in.nextInt();
                    Metodoak.datuaEzabatu(id);
                    break;
                case 4:
                    String izenBerria;
                    System.out.print("Sartu aldtu nahi duzun artistaren id-a: ");
                    id = in.nextInt();
                    System.out.print("Sartu izen berria: ");
                    izenBerria = in.next();
                    Metodoak.datuakEguneratu(id, izenBerria);
                    break;
                case 5:
                    Metodoak.datuakIkusiOrdenatuta();
                    break;
                case 6:
                    int idArtist;
                    System.out.print("Sartu ikusi nahi duzun artistaren id-a: ");
                    idArtist = in.nextInt();
                    Metodoak.datuBakarra(idArtist);
                    break;
                case 7:
                    String letra;
                    System.out.print("Sartu letra bat: ");
                    letra = in.next();
                    Metodoak.datuakIkusiLetrarekin(letra);
                    break;
                case 8:
                    int zb;
                    System.out.print("Sartu artistaren id-a: ");
                    zb = in.nextInt();
                    Metodoak.diskakErakutsi(zb);
                    break;
                case 9:
                    Metodoak.generoakIkusi();
                    break;
                case 10:
                    Metodoak.abestiakGenero();
                    break;

                default:
                    System.out.println("Aukera hori ez dago prest, erabaki beste bat");

            }
        }
        System.out.println("Agur!!");

    }

}
