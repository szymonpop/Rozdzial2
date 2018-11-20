package Homeworks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klasa ogłaszająca wynik gry w kołko i krzyżyk. Kółko reprezentowane przez -1. X przez +1.
 *
 */
public class KolkoKrzyzyk {
    /**
     * Wczytuje liczby 1, 0 lub -1 oznaczające kolejno: krzyżyk, puste pole, kółko.
     * @return zwraca tablice 2d przedstawiającą planszę 3x3 gry
     */
    public static int[][] LoadGameFromKeyboard(){
        int[][] tabelka = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                tabelka[i][j]=scanner.nextInt();

            }
        }
        return tabelka;
    }

    /**
     * Wczytuje plik zawierający:
     * liczby 1, 0 lub -1 oznaczające kolejno: krzyżyk, puste pole, kółko.
     * Liczby oddzielane są tylko przecinkami
     * @return zwraca tablice 2d przedstawiającą planszę 3x3 gry
     * @return tabelka - gdy jest prawidlowa liczba danych, tabelkaPiatek - gdy liczba danych nieprawidłowa lub
     * gdy dane zawierają liczbę,która nie jest 1,0 lub -1.
     * @throws FileNotFoundException
     */
    public static int[][] LoadGameFromTxt() throws FileNotFoundException {
        File inFile = new File("C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\DoKolkaIKrzyzyka.txt");
        int[][] tabelka = new int[3][3];
        int[][] tabelkaPiatek = new int[3][3];
        for(int i =0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                tabelkaPiatek[i][j] = 5;
            }
        }
        Scanner scanner = new Scanner(inFile);
        int i=0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] elems = line.split(",");

         for (int j=0; j<3; j++){
             tabelka[i][j]=Integer.parseInt(elems[j]);
         }
         i++;
        }

/*
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                if(scanner.hasNextInt())
                tabelka[i][j]=scanner.nextInt();
                if (Math.pow(tabelka[i][j],2)>1){
                    return tabelkaPiatek;
                }
            }
        }
        */
        return tabelka;
    }

    /**
     * Sprawdza czy gracze mają możliwość wypełnić jakieś pola na planszy
     * @param tabelka - wprowadzona gra
     * @return - zwraca true jeśli gracze mają możliwość wprowadzenia danych na planszę,
     */
    public static boolean NadalTrwa(int[][]tabelka){
        boolean wynik= false;
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                if(tabelka[i][j]==0){
                    wynik=true;
                    break;
                }


            }
        }
        return wynik;
    }

    /**
     * Metoda sprawdza wynik gry.
     * 1. sprawdza czy wprowadzone dane są prawidłowe. Jeżeli nie są to parametr gra=tabelkaPiatek i metoda wyświetla komunikat o niepoprawności
     * 2. Sprawdz wiersze, kolumny i przekątne planszy w poszukiwaniu 3x1 lub 3x-1.
     * 3. Jeśli wynik nie jest ustalony, to sprawdza czy są jakieś wolne pola do wypełnienia metodą NadalTrwa();
     * @param gra - wczytana plansza kółka i krzyżyki
     * @param nadalTrwa- do sprawdzenia czy zostały pola do wypełnienia przez graczy
     * @return
     */

    public static int CheckWin(int[][] gra, boolean nadalTrwa)  {
        int wynik = 0;
        int max = 0;
        int min= 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                max = Math.max(gra[i][j],max);
                min = Math.min(gra[i][j],min);
            }
        }
        if(max>1){
            wynik = 5;
        }
        if(min<-1){
            wynik = 5;
        }


            for (int j = 0; j < 3; j++) {
                int checkrow = 0;
                int checkcolumn=0;
                for (int i = 0; i < 3; i++) {
                    checkrow += gra[i][j];
                    checkcolumn += gra[j][i];
                }
                    if (checkrow == 3) {
                        wynik = 1;break;
                    } else if (checkrow == -3) {
                        wynik = -1;break;
                    } else if (checkcolumn == 3) {
                        wynik = 1;break;
                    } else if (checkcolumn == -3) {
                        wynik = -1;break;
                    } else if (gra[0][0] + gra[1][1] + gra[2][2] == 3) {
                        wynik = 1;break;
                    } else if (gra[0][0] + gra[1][1] + gra[2][2] == -3) {
                        wynik = -1;break;
                    } else if (gra[0][2] + gra[1][1] + gra[2][0] == -3) {
                        wynik = -1;break;
                    } else if (gra[0][2] + gra[1][1] + gra[2][0] == 3) {
                        wynik = 1;break;
                    } else if (nadalTrwa) {
                        System.out.print("Gra nadal trwa");
                        return 0;
                    }

            }

            if(wynik==1) {
                System.out.print("wygrały X");
            }
            else if(wynik==-1){
                System.out.print("wygrały O");

            }else if(wynik==0){
                System.out.print("Remis");
            }
            else{
                System.out.print("coś nie tak- wystąpiły dane, które nie są z {-1, 0, 1");
            }
            return wynik;
        }

}
