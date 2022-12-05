import java.io.*;
import java.util.*;

public class TestLabyrint {
  public static void main(String[] args) {

    File fil  = new File("2.in");
    Labyrint labyrint1 = null;
    try {
    labyrint1 = labyrint1.lesFraFil(fil);
  } catch (FileNotFoundException e) {
    System.out.println("Finner ikke fil");
  }
    Labyrint labyrint = labyrint1.hentLabyrint();
    int rader = labyrint1.hentRader();
    int kolonner = labyrint1.hentKolonner();
    int tellerR = 0;
    for (int r = 0; r < rader; r++) {
            int tellerK = 0;
      for (int k = 0; k < kolonner; k++) {
        Rute rute = labyrint.hentRute(tellerR, tellerK);
        System.out.print(rute.toString());
        tellerK++;
      }
      System.out.println();
      tellerR++;
    }

  //  labyrint.finnUtveiFra(7, 2);


    //Rute rute = labyrint.hentRute(3, 1);
    //System.out.println(rute.finnUtvei());
    Lenkeliste<String> utveier = labyrint.finnUtveiFra(5, 5);
    labyrint.skrivUtUtveier();
    //labyrint.skrivUtUtveier();
/*
    Rute rute1 = labyrint[0][0];
    System.out.println("rute 0,0, indexer: " + rute1.hentRad() + ", " + rute1.hentKolonne());
    rute1.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute2 = labyrint[4][0];
    System.out.println("rute 2,0, indexer: " + rute2.hentRad() + ", " + rute2.hentKolonne());
    rute2.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute3 = labyrint[0][4];
    System.out.println("rute 0,2, indexer: " + rute3.hentRad() + ", " + rute3.hentKolonne());
    rute3.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute4 = labyrint[4][4];
    System.out.println("rute 2,2, indexer: " + rute4.hentRad() + ", " + rute4.hentKolonne());
    rute4.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute5 = labyrint[2][2];
    System.out.println("rute 1,1, indexer: " + rute5.hentRad() + ", " + rute5.hentKolonne());
    rute5.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute6 = labyrint[4][2];
    System.out.println("rute 0,1, indexer: " + rute6.hentRad() + ", " + rute6.hentKolonne());
    rute6.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute7 = labyrint[0][3];
    System.out.println("rute 1,0, indexer: " + rute7.hentRad() + ", " + rute7.hentKolonne());
    rute7.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute8 = labyrint[3][4];
    System.out.println("rute 1,2, indexer: " + rute8.hentRad() + ", " + rute8.hentKolonne());
    rute8.SkrivUtVedSidenRuter();
    System.out.println(" ");

    Rute rute9 = labyrint[3][3];
    System.out.println("rute 2,1, indexer: " + rute9.hentRad() + ", " + rute9.hentKolonne());
    rute9.SkrivUtVedSidenRuter();


*/



  }
}
