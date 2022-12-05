import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ArrayList;

public class Lenkeliste<T> implements Liste<T> {
  protected Node start;

  public Lenkeliste(){
    start = new Node(null);
  }

//klasse som har lagrer neste referansen i lenkelisten
  public class Node {
    protected T element;
    protected Node neste;

    public Node(T x) {
      element = x;
    }

    public T hentElement() {
      return element;
    }
  }

 public Node hentStart() {
   return start;
 }

/*
sjekker storrelsen av lenkelisten og returnerer tallet
*/
  public int stoerrelse() {
    Node element = start;
    int s = 0;
    while (element.neste != null) {
      s++;
      element = element.neste;
    }
    return s;
  }

/*
skal legge inn et nytt element i listen og skyve neste element ett hakk lenger bak ved en
gitt indeks
*/
  public void leggTil(int pos, T x) {
    Node ny = new Node(x);
    Node element = start;

    if (pos > stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }
      int teller = 0;

      while(teller < pos && element.neste != null) {
        element = element.neste;
        teller++;

      }
      /*
      legger til referansen til den neste elementet til det nye elementet, og det
      forriges referanse lik det nye elementet, på den maaten flytter hele lista et
      hakk ned
      */
          ny.neste = element.neste;
          element.neste = ny;
          return;
  }

/*
legger til et element paa enden av listen - eller starten, om start er null
*/
  public void leggTil(T x) {
    Node ny = new Node(x);
    Node element = start;
    if(start.neste == null) {
      start.neste = ny;
    } else {
      while (element.neste != null) {
        element = element.neste;
      }
        element.neste = ny;

    }
  }

  /*
  sette inn elementet paa en gitt posisjon og overskrive det som var der
  fra foor av
  */
  public void sett(int pos, T x) throws UgyldigListeIndeks {
    Node ny = new Node(x);
    Node element = start;

    if(pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

      int teller = 0;

//finner frem til elementet foor det som skal erstattes
      while(teller < pos && element.neste != null) {
        element = element.neste;
        teller++;
      }
  /**
  legger til referansen til nestes neste i det nye elementet i den aktuelle indexen
  og setter forrige til aa peke paa det nye elementet, da fjerner det gamle
  elementet som senere hentes av javas garbage collector
  */
      ny.neste = element.neste.neste;
      element.neste = ny;

  }

/*
henter ut et element (uten aa fjerne det) fra gitt posisjon
*/
  public T hent(int pos) throws UgyldigListeIndeks {
    Node element = start;
    Node hentElem = null;

    if (pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

    int teller = 0;
//finner frem til elementet foor det som skal hentes
    while(teller < pos && element.neste != null) {
        element = element.neste;
        teller++;
      }

  /*
  Henter elementet uten aa fjerne det og returnerer det som skal hentes
  */
    hentElem = element.neste;
    return hentElem.hentElement();
  }

/*
skal fjerne paa gitt indeks i listen
*/
  public T fjern(int pos) throws UgyldigListeIndeks {
      Node skalFjernes;
      Node element = start;
      int teller = 0;

    if (pos >= stoerrelse() || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }

//finner frem til elementet foor det som skal erstattes
      while(teller < pos && element.neste != null) {
        element = element.neste;
        teller++;
      }
      skalFjernes = element.neste;
      element.neste = element.neste.neste;
      return skalFjernes.hentElement();
  }

/*
Skal fjerne og returnere elementet i starten av listen
*/
  public T fjern() throws UgyldigListeIndeks {
    Node skalFjernes = null;
    if (start.neste == null) {
      throw new UgyldigListeIndeks(0);
    }
      skalFjernes = start.neste;
      start.neste = start.neste.neste;
      return skalFjernes.hentElement();
  }

//putter elementene i liste og returnerer listen - for å hente elementene (oblig 4) (endre navn)
  public ArrayList<T> hentListe() {
    ArrayList<T> elementene = new ArrayList<T>();
    for (T element: this) {
    //  T elementet = element.hentElement();
      elementene.add(element);
    }
    return elementene;
/*
    Node element = start;
    while (element.neste != null){
      T elementet = element.hentElement();
      elementene.add(elementet);
      element = element.neste;.
    }*/

  }

//printer ut listen med elementer T (oblig 4)
  public void printListe() {
    for (T element: this) {
    //  T elem = element.hentElement();
      System.out.println(element.toString());
    }
/*
    Node element = start;
    while (element.neste != null){
      System.out.println(T.toString());
      T elementet = element.hentElement();
      element = element.neste;
    } */
  }


//Iterator implementasjon under (oblig 4)
  public LenkelisteIterator iterator() {
    return new LenkelisteIterator();
  }


//Lenkelisteiteratoren (oblig 4)
  private class LenkelisteIterator implements Iterator<T> {
    private Node posisjon;

    public LenkelisteIterator() {
      posisjon = start;
    }

  @Override
    public boolean hasNext() {
      return posisjon.neste != null;
    }

  @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      posisjon = posisjon.neste;
      T element = posisjon.hentElement();
      return element;
    }

  @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
