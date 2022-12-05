public class Aapning extends HvitRute {
  protected char character = 'x';

  public Aapning(int kolonne, int rad, Labyrint labyrint) {
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
