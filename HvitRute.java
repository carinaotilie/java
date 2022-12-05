public class HvitRute extends Rute {
  protected char character = '.';

  public HvitRute(int kolonne, int rad, Labyrint labyrint) {
    super(kolonne, rad, labyrint);
  }

@Override
  public char tilTegn() {
    return character;
  }

@Override
  public String toString() {
    return " . ";
  }

}
