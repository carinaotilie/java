import java.util.*;

abstract class Rute {
  protected int kolonne;
  protected int rad;
//labyrinten, referanse
  protected Labyrint labyrint;
//eventuelle naboruter
  protected Rute nord = null;
  protected Rute syd = null;
  protected Rute ost = null;
  protected Rute vest = null;

  public Rute (int kolonne, int rad, Labyrint labyrint) {
      this.kolonne = kolonne;
      this.rad =  rad;
      this.labyrint = labyrint;
  }

  //for aa sette info om rutene ved siden av ruten

  public void settInfoRuter(Rute n, Rute s, Rute o, Rute v) {
    nord = n;
    syd = s;
    ost = o;
    vest = v;
  }

//brukt til aa sjekke at rutene ved siden av ruter ble satt inn korekt i eget test-program
  protected void SkrivUtVedSidenRuter() {
    try {
      int r = nord.hentRad();
      int k = nord.hentKolonne();
      System.out.println("Nord (r,k): " + r + ", " + k);
    } catch (NullPointerException e) {
      System.out.println("ingen mot nord");
    }
    try {
      int r = syd.hentRad();
      int k = syd.hentKolonne();
      System.out.println("Syd (r,k): " + r + ", " + k);
    } catch (NullPointerException e) {
      System.out.println("ingen mot syd");
    }
    try {
      int r = ost.hentRad();
      int k = ost.hentKolonne();
      System.out.println("Ost (r,k): " + r + ", " + k);
    } catch (NullPointerException e) {
      System.out.println("ingen mot ost");
    }
    try {
      int r = vest.hentRad();
      int k = vest.hentKolonne();
      System.out.println("Vest (r,k): " + r + ", " +k);
    } catch (NullPointerException e) {
      System.out.println("ingen mot vest");
    }

  }

//skal gaa til neste rute og neste rute av seg selv og finne en utvei
  public void gaa(Rute rute, String utvei, Lenkeliste<String> liste) {
    String leggTil = " -> (" + this.kolonne + ", " + this.rad + ")";
    utvei = utvei + leggTil;

    if (this.tilTegn() == 'x') {
      liste.leggTil(utvei);
        return;

    } else if (this.tilTegn() == '#') {
        return;

    } else {

      if (nord != null && !nord.equals(rute)){
        nord.gaa(this, utvei, liste);
      } if (syd != null && !syd.equals(rute)) {
        syd.gaa(this, utvei, liste);
      } if (ost != null && !ost.equals(rute)) {
        ost.gaa(this, utvei, liste);
      } if(vest != null && !vest.equals(rute)) {
        vest.gaa(this, utvei, liste);
      } else {
          return;
        }
      }
  }



//finner alle utveier fra ruten ved hjelp av kall paa gaa
  public void finnUtvei(Lenkeliste<String> listeUtveier) {
    String utvei = "";

    gaa(this, utvei, listeUtveier);
  }


  public int hentKolonne() {
    return kolonne;
  }

  public int hentRad() {
    return rad;
  }


  abstract char tilTegn();


}
