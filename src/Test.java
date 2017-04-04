/**
 * Created by duviteck on 19/12/15.
 */
public class Test {

  public static void main(String[] args) {
    double d = 6.327d;
    System.out.println(d);
    System.out.println(Math.floor(d));

    int pow = (int) Math.pow(10, 2);
    System.out.println(Math.floor(d * pow) / pow);


    System.out.println("b".compareTo("aa"));

    String[] tokens = " a b ".split(" ");
    for (String s : tokens) {
      System.out.println("#" + s + "#");
    }
  }
}
