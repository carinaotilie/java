import java.util.*;
import java.io.*;

public class Labyrint {
    protected Rute[][] labyrint;
    protected int kolonner;
    protected int rader;
    public Lenkeliste<String> listeUtveier;

    private Labyrint(Rute[][] labyrint, int kolonner, int rader) {
      settInfoOmRuter(labyrint, rader, kolonner);
      this.kolonner = kolonner;
      this.rader = rader;
      this.labyrint  = labyrint;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
      Labyrint labyrint = null;

      Scanner scanner = new Scanner(fil);

      int tellerRad = 0;
      String innlest = scanner.nextLine();
      String[] stoerrelse = innlest.split(" ");
      int rad = Integer.parseInt(stoerrelse[0]);
      int kolonne = Integer.parseInt(stoerrelse[1]);
      Rute[][] midLab = new Rute[rad][kolonne];


//leser inn fra fil
      while (scanner.hasNextLine()) {
        for (int r = 0; r < rad; r++) {
          innlest = scanner.nextLine();
          for(int k = 0; k < kolonne; k++) {
            if (innlest.charAt(k) == '#') {
              midLab[r][k] = new SortRute(k, r, labyrint);
            } else if (innlest.charAt(k) == '.') {
              if (sjekkOmApning(r, k, rad, kolonne)) {
                midLab[r][k] = new Aapning(k, r, labyrint);
              } else {
                midLab[r][k] = new HvitRute(k, r, labyrint);
              }
            }
          }
        }
      }

      //kaller paa den private kosntruktoren for opprette dette labyrint objektet
      labyrint = new Labyrint(midLab, kolonne, rad);
      return labyrint;

    }

    public static Boolean sjekkOmApning(int r, int k, int rad, int kolonne) {
      if (r == 0 || r == rad-1 || k == 0 || k == kolonne-1) {
        return true;
      } else {
        return false;
      }
    }

//alle ruter maa ha kjenskap til ruten ved siden av seg
    protected void settInfoOmRuter(Rute[][] labyrint, int rad, int kolonne) {
      Rute[] ruter = null;
      Rute nord = null;
      Rute syd = null;
      Rute ost = null;
      Rute vest = null;
      Rute rute = null;
      int tellerK = 0;
      int tellerR = 0;
      for(int r  = 0; r < rad; r++) {
        ruter = labyrint[r];
        for(int k = 0; k < kolonne; k++) {
          nord = null;
          syd = null;
          ost = null;
          vest = null;
          rute = ruter[k];
          int raden = rute.hentRad();
          int kolonnen = rute.hentKolonne();
          if (raden == 0 && kolonnen == 0) { //dersom den er i venstre hjorne - overst
            tellerK = kolonnen+1;
            tellerR = raden+1;
            syd = labyrint[tellerR][kolonnen];
            ost = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (raden == rad-1 && kolonnen == 0) { //venstre hjorne nederst
            tellerK = kolonnen+1;
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            ost = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (raden == 0 && kolonnen == kolonne-1) { //hoyre hjornet overst
            tellerK = k-1;
            tellerR = r+1;
            syd = labyrint[tellerR][kolonnen];
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (raden == rad-1 && kolonnen == kolonne-1) { //hoyre hjornet nederst
            tellerK = kolonnen-1;
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (raden == 0 && kolonnen != 0 && kolonnen != kolonne-1) { //midten ovre rad
            tellerR = raden+1;
            syd = labyrint[tellerR][kolonnen];
            tellerK = kolonnen+1;
            ost = labyrint[raden][tellerK];
            tellerK = kolonnen-1;
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (raden == rad-1 && kolonnen != 0 && kolonnen != kolonne-1) {
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            tellerK = kolonnen+1;
            ost = labyrint[raden][tellerK];
            tellerK = kolonnen-1;
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (kolonnen == 0 && raden != 0 && raden != rad-1) { //venstre side - midt
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            tellerR = raden+1;
            syd = labyrint[tellerR][kolonnen];
            tellerK = kolonnen+1;
            ost = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else if (kolonnen == kolonne-1 && raden != 0 && raden != rad-1) {//hoyrde side - midten
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            tellerR = raden+1;
            syd = labyrint[tellerR][kolonnen];
            tellerK = kolonnen-1;
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          } else { //midten -som ikke har en side som grenser til utsiden
            tellerR = raden-1;
            nord = labyrint[tellerR][kolonnen];
            tellerR = raden+1;
            syd = labyrint[tellerR][k];
            tellerK = kolonnen+1;
            ost = labyrint[raden][tellerK];
            tellerK = kolonnen-1;
            vest = labyrint[raden][tellerK];
            rute.settInfoRuter(nord, syd, ost, vest);
          }
        }
      }
    }

//for aa hente labyrinten - brukes for aa skrive ut labyrinten i test-program
    public Labyrint hentLabyrint() {
      return this;
    }

//henter antall kolonner
    public int hentKolonner() {
      return kolonner;
    }
//henter antall rader
    public int hentRader() {
      return rader;
    }

    public Rute hentRute(int rad, int kolonne) {
      return labyrint[rad][kolonne];
    }

//skal finne utvei ut fra labyrint ved aa sende inn kordinater paa rute, kolonne
    public Lenkeliste<String> finnUtveiFra(int kolonne, int rad) {
      Rute rute = labyrint[rad][kolonne];
      listeUtveier =  new Lenkeliste<String>();
      rute.finnUtvei(listeUtveier);
      return listeUtveier;

    }

    public void skrivUtUtveier() {
      if (listeUtveier.stoerrelse() == 0
      ) {
        System.out.println("Ingen utveier");
      }
      for (int i = 0; i < listeUtveier.stoerrelse(); i++) {
        System.out.println(listeUtveier.hent(i));
      }
    }
}
