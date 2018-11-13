import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException  {

        //   LocalDateUse.WyswietlanieKalendarza(args); // Wyswietla kalendarz na wprowadzony w argumencie miesiac
        /*
        Point p= new Point(3,4).translate(1,3).scale(0.5);   //metody dostępowe
        System.out.print(p.Getx() + " " + p.Gety());
        */
        /*
        Point p= new Point(3,4);
        p.translateMod(1,3);
        p.scaleMod(0.5);                                    //metody modyfikujące
        System.out.print(p.Getx() + " " + p.Gety());
*/
       // System.out.print(ProgramPawla.NajblizejSredniej());
/*
        Car Auto1 = new Car(7,0,50);
        Auto1.setPozycja(50,"prawo");
        Auto1.setPozycja(150,"lewo");
        Auto1.setStanBenzyny(20);
        System.out.print(Auto1.getPozycja()+"\n"+Auto1.getStanBenzyny()+"\n"+ Auto1.getIlePrzejechales());
        */

/*
        Random generator = new Random();
        int[] tablica = new int[5];
        for(int i=0; i<5;i++){
            tablica[i]=generator.nextInt(20000);
        }                                                               //Klasa RandomNumbers. TAblica int[]
        for(int i=0; i<5;i++){
            System.out.print(tablica[i]);
            System.out.print("  ");
        }

        for (int i =0; i<10; i++){
            System.out.println("\n");
            System.out.print(RandomNumbers.RandomElement(tablica));

        }
*/
/*
        Random generator = new Random();
        ArrayList tablica = new ArrayList<Integer>();
        for(int i=0; i<5;i++){
            tablica.add(generator.nextInt(20000));
        }                                                               //Klasa RandomNumbers. Tablica Integer
        for(int i=0; i<5;i++){
            System.out.print(tablica.get(i));
            System.out.print("  ");
        }

        for (int i =0; i<10; i++){
            System.out.println("\n");
            System.out.print(RandomNumbers.RandomElement(tablica));

        }
*/
        int[][] gra=new int[3][3];
        gra=KolkoKrzyzyk.LoadGameFromTxt();
        KolkoKrzyzyk.CheckWin(gra,KolkoKrzyzyk.NadalTrwa(gra));




    }
}
