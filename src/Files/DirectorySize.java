package Files;

import java.io.File;

public class DirectorySize {
    private static long licznikDanych=0;


    public static void CheckDirectorySize(File newFile) {

        if (newFile.isDirectory()) {
            File[] files = newFile.listFiles();
            System.out.println("W folderze "+ newFile.getName()+" są "+ files.length+" pliki:");
            for (File f : files) {
                if(f.isFile()) {
                    System.out.println("Plik "+f.getName() + " rozmiar:" + f.length());
                    licznikDanych += f.length();
                }else if (f.isDirectory()){
                    System.out.println("Podfolder "+ f.getName());
                    CheckDirectorySize(f);
                }
            }
            System.out.print("Folder "+newFile.getName()+" waży: " + licznikDanych+"\n");
        }
    }
    public static void main(String[] args){
        File newFile= new File("C:/temp");
        CheckDirectorySize(newFile);


        }

}
