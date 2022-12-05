public class SortRute extends Rute {
  protected char character = '#';

  public SortRute(int kolonne, int rad, Labyrint labyrint) {
    super(kolonne, rad, labyrint);
  }

@Override
  public char tilTegn() {
    return character;
  }
@Override
    public String toString() {
      return " # ";
    }

}
