/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static probak.Menua.sf;

/**
 *
 * @author Joseba
 */
public class Metodoak {

    public static void datuaGorde(Artist a) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        saioa.save(a);
        saioa.getTransaction().commit();
        saioa.close();

    }

    public static void datuaEzabatu(int idArtista) {
        Session saioa = null;
        Artist ik = null;
        Transaction tx = null;
        try {
            saioa = sf.openSession();
            tx = saioa.beginTransaction();
            ik = (Artist) saioa.load(Artist.class, idArtista);
            saioa.delete(ik);
            tx.commit();
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Artista hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }

    public static void datuakEguneratu(int idArtista, String izenBerria) {
        Session saioa = null;
        Artist ik = null;
        Transaction tx = null;
        try {
            saioa = sf.openSession();
            tx = saioa.beginTransaction();
            ik = (Artist) saioa.load(Artist.class, idArtista);
            ik.setName(izenBerria);
            tx.commit();
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Artista hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }

    public static void datuakIkusi() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artist").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Artist a : (List<Artist>) result) {
            System.out.println("*"+a);
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void datuakIkusiOrdenatuta() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artist ORDER BY name ASC").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Artist a : (List<Artist>) result) {
            System.out.println(a.getName());
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void datuakIkusiLetrarekin(String letra) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artist WHERE name like '" + letra + "%'").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        System.out.println(letra.toUpperCase());
        for (Artist a : (List<Artist>) result) {
            System.out.printf("%3d|%s\n", a.getArtistid(), a.getName());
        }
        System.out.println("======================================================");
        System.out.println(result.size() + " artista daude " + letra.toUpperCase() + " letrarekin.");
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void datuBakarra(int idArtista) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artist WHERE artistid = " + idArtista).list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Artist a : (List<Artist>) result) {
            System.out.println(a);
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static String artistIzena(int idArtista) {
        String izena = "";
        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artist WHERE artistid = " + idArtista).list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Artist a : (List<Artist>) result) {
            izena = a.getName();
        }
        saioa.getTransaction().commit();
        saioa.close();
        return izena;
    }

    public static void diskakErakutsi(int artistId) {
        String sql = "Select Title from Album where ArtistId = " + artistId;
        int i = 1;
        try ( Connection conn = connect("chinook");  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println("======================================================");
            System.out.println("Artista=>" + artistIzena(artistId));
            System.out.println("======================================================");
            System.out.println("Diskak");

            while (rs.next()) {
                System.out.printf("%2d|%s\n", i, rs.getString("Title"));
                i++;
            }
            System.out.println("Diska kopurua: " + (i - 1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void abestiakGenero() {
        ArrayList<String> alt = new ArrayList<>();
        ArrayList<String> altP = new ArrayList<>();
        ArrayList<String> blues = new ArrayList<>();
        ArrayList<String> bossa = new ArrayList<>();
        ArrayList<String> classic = new ArrayList<>();
        ArrayList<String> com = new ArrayList<>();
        ArrayList<String> drama = new ArrayList<>();
        ArrayList<String> el = new ArrayList<>();
        ArrayList<String> ed = new ArrayList<>();
        ArrayList<String> hm = new ArrayList<>();
        ArrayList<String> hhr = new ArrayList<>();
        ArrayList<String> jazz = new ArrayList<>();
        ArrayList<String> latin = new ArrayList<>();
        ArrayList<String> metal = new ArrayList<>();
        ArrayList<String> opera = new ArrayList<>();
        ArrayList<String> pop = new ArrayList<>();
        ArrayList<String> rbs = new ArrayList<>();
        ArrayList<String> reggae = new ArrayList<>();
        ArrayList<String> rock = new ArrayList<>();
        ArrayList<String> rar = new ArrayList<>();
        ArrayList<String> scfif = new ArrayList<>();
        ArrayList<String> scifi = new ArrayList<>();
        ArrayList<String> st = new ArrayList<>();
        ArrayList<String> tv = new ArrayList<>();
        ArrayList<String> world = new ArrayList<>();

        String sql = "SELECT track.Name, genre.name FROM track INNER JOIN genre ON track.Genreid = genre.GenreId";
        try ( Connection conn = connect("chinook");  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                if (rs.getString("genre.Name").equals("Alternative")) {
                    String track = rs.getString("track.Name");
                    alt.add(track);
                } else if (rs.getString("genre.Name").equals("Alternative & Punk")) {
                    String track = rs.getString("track.Name");
                    altP.add(track);
                } else if (rs.getString("genre.Name").equals("Blues")) {
                    String track = rs.getString("track.Name");
                    blues.add(track);
                } else if (rs.getString("genre.Name").equals("Bossa Nova")) {
                    String track = rs.getString("track.Name");
                    bossa.add(track);
                } else if (rs.getString("genre.Name").equals("Classical")) {
                    String track = rs.getString("track.Name");
                    classic.add(track);
                } else if (rs.getString("genre.Name").equals("Comedy")) {
                    String track = rs.getString("track.Name");
                    com.add(track);
                } else if (rs.getString("genre.Name").equals("Drama")) {
                    String track = rs.getString("track.Name");
                    drama.add(track);
                } else if (rs.getString("genre.Name").equals("Easy Listening")) {
                    String track = rs.getString("track.Name");
                    el.add(track);
                } else if (rs.getString("genre.Name").equals("Electronica/Dance")) {
                    String track = rs.getString("track.Name");
                    ed.add(track);
                } else if (rs.getString("genre.Name").equals("Heavy Metal")) {
                    String track = rs.getString("track.Name");
                    hm.add(track);
                } else if (rs.getString("genre.Name").equals("Hip Hop/Rap")) {
                    String track = rs.getString("track.Name");
                    hhr.add(track);
                } else if (rs.getString("genre.Name").equals("Jazz")) {
                    String track = rs.getString("track.Name");
                    jazz.add(track);
                } else if (rs.getString("genre.Name").equals("Latin")) {
                    String track = rs.getString("track.Name");
                    latin.add(track);
                } else if (rs.getString("genre.Name").equals("Metal")) {
                    String track = rs.getString("track.Name");
                    metal.add(track);
                } else if (rs.getString("genre.Name").equals("Opera")) {
                    String track = rs.getString("track.Name");
                    opera.add(track);
                } else if (rs.getString("genre.Name").equals("Pop")) {
                    String track = rs.getString("track.Name");
                    pop.add(track);
                } else if (rs.getString("genre.Name").equals("R&B/Soul")) {
                    String track = rs.getString("track.Name");
                    rbs.add(track);
                } else if (rs.getString("genre.Name").equals("Reggae")) {
                    String track = rs.getString("track.Name");
                    reggae.add(track);
                } else if (rs.getString("genre.Name").equals("Rock")) {
                    String track = rs.getString("track.Name");
                    rock.add(track);
                } else if (rs.getString("genre.Name").equals("Rock And Roll")) {
                    String track = rs.getString("track.Name");
                    rar.add(track);
                } else if (rs.getString("genre.Name").equals("Sci Fi & Fantasy")) {
                    String track = rs.getString("track.Name");
                    scfif.add(track);
                } else if (rs.getString("genre.Name").equals("Science Fiction")) {
                    String track = rs.getString("track.Name");
                    scifi.add(track);
                } else if (rs.getString("genre.Name").equals("Soundtrack")) {
                    String track = rs.getString("track.Name");
                    st.add(track);
                } else if (rs.getString("genre.Name").equals("TV Shows")) {
                    String track = rs.getString("track.Name");
                    tv.add(track);
                } else if (rs.getString("genre.Name").equals("World")) {
                    String track = rs.getString("track.Name");
                    world.add(track);
                }

            }
            System.out.println("=========================================");
            System.out.println("Alternative");
            System.out.println("=========================================");
            for (int i = 0; i < alt.size(); i++) {
                System.out.println("-" + alt.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Alternative & Punk");
            System.out.println("=========================================");
            for (int i = 0; i < altP.size(); i++) {
                System.out.println("-" + altP.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Blues");
            System.out.println("=========================================");
            for (int i = 0; i < blues.size(); i++) {
                System.out.println("-" + blues.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Bossa Nova");
            System.out.println("=========================================");
            for (int i = 0; i < bossa.size(); i++) {
                System.out.println("-" + bossa.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Classical");
            System.out.println("=========================================");
            for (int i = 0; i < classic.size(); i++) {
                System.out.println("-" + classic.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Comedy");
            System.out.println("=========================================");
            for (int i = 0; i < com.size(); i++) {
                System.out.println("-" + com.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Drama");
            System.out.println("=========================================");
            for (int i = 0; i < drama.size(); i++) {
                System.out.println("-" + drama.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Easy Listening");
            System.out.println("=========================================");
            for (int i = 0; i < el.size(); i++) {
                System.out.println("-" + el.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Electronica/Dance");
            System.out.println("=========================================");
            for (int i = 0; i < ed.size(); i++) {
                System.out.println("-" + ed.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Heavy Metal");
            System.out.println("=========================================");
            for (int i = 0; i < hm.size(); i++) {
                System.out.println("-" + hm.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Hip Hop/Rap");
            System.out.println("=========================================");
            for (int i = 0; i < hhr.size(); i++) {
                System.out.println("-" + hhr.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Jazz");
            System.out.println("=========================================");
            for (int i = 0; i < jazz.size(); i++) {
                System.out.println("-" + jazz.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Latin");
            System.out.println("=========================================");
            for (int i = 0; i < latin.size(); i++) {
                System.out.println("-" + latin.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Metal");
            System.out.println("=========================================");
            for (int i = 0; i < metal.size(); i++) {
                System.out.println("-" + metal.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Opera");
            System.out.println("=========================================");
            for (int i = 0; i < opera.size(); i++) {
                System.out.println("-" + opera.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Pop");
            System.out.println("=========================================");
            for (int i = 0; i < pop.size(); i++) {
                System.out.println("-" + pop.get(i));
            }
            System.out.println("=========================================");
            System.out.println("R&B/Soul");
            System.out.println("=========================================");
            for (int i = 0; i < rbs.size(); i++) {
                System.out.println("-" + rbs.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Reggae");
            System.out.println("=========================================");
            for (int i = 0; i < reggae.size(); i++) {
                System.out.println("-" + reggae.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Rock");
            System.out.println("=========================================");
            for (int i = 0; i < rock.size(); i++) {
                System.out.println("-" + rock.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Rock And Roll");
            System.out.println("=========================================");
            for (int i = 0; i < rar.size(); i++) {
                System.out.println("-" + rar.get(i));
            }

            System.out.println("=========================================");
            System.out.println("Sci Fi & Fantasy");
            System.out.println("=========================================");
            for (int i = 0; i < scfif.size(); i++) {
                System.out.println("-" + scfif.get(i));
            }

            System.out.println("=========================================");
            System.out.println("Science Fiction");
            System.out.println("=========================================");
            for (int i = 0; i < scifi.size(); i++) {
                System.out.println("-" + scifi.get(i));
            }
            System.out.println("=========================================");
            System.out.println("Soundtrack");
            System.out.println("=========================================");
            for (int i = 0; i < st.size(); i++) {
                System.out.println("-" + st.get(i));
            }
            System.out.println("=========================================");
            System.out.println("TV Shows");
            System.out.println("=========================================");
            for (int i = 0; i < tv.size(); i++) {
                System.out.println("-" + tv.get(i));
            }
            System.out.println("=========================================");
            System.out.println("World");
            System.out.println("=========================================");
            for (int i = 0; i < world.size(); i++) {
                System.out.println("-" + world.get(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generoakIkusi() {
        String sql = "Select name from genre order by name ASC";
        try ( Connection conn = connect("chinook");  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println("=============================================================================");
            System.out.println("Generoak");
            System.out.println("=============================================================================");

            int zutabeak = 0;
            int count = 0;
            while (rs.next()) {
                if (zutabeak == 2) {
                    System.out.printf("*%20s\n", rs.getString("name"));
                    zutabeak = 0;
                } else {
                    System.out.printf("*%20s ", rs.getString("name"));
                    zutabeak++;
                }
                count++;
            }
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println("Guztira: " + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect(String db) {
        String server = "localhost";
        String url = "jdbc:mysql://" + server + "/" + db;

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, "root", "root");
            //System.out.println("datubasera konektatu zara!");
        } catch (SQLException e) {
            System.out.println("Errorea agertu da datubasearekin konektatzerako orduan " + e.getMessage());
        }
        return con;
    }
}
