package Homeworks.Homework3FilesOper;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


import static java.lang.Double.valueOf;


/**  * Klasa służąca do dodawania do pliku csv z danymi o historii notowań przedsiębiorstwa dodatkowej pozycji.
 * @author Szymon
 * @Źródła: github PRubach,
 * https://stackoverflow.com/questions/12217087/add-a-specific-string-at-the-end-of-a-line-of-file-in-java
 * https://community.liferay.com/forums/-/message_boards/view_message/76571457
 */


public class SciagnijIPrzerabiaj {
    private static final int BUFFER_SIZE = 4096;
    /**
     * @param yourFile przechowuje plik, którego dotyczy obiekt klasy
     * @param numLInes przechowuje liczbę linijek pliku
     * @param lines przechowuje tablicę stringów z liniami danego pliku
     */

    private File yourFile = new File("YourFileHere.txt"); //add the name of your file in the brackets
    private int numLines; //this will store the number of lines in the file
    private String[] lines; //the lines of text that make up the file will be stored here
/*
    /**
     * Sciaga plik i zapisuje go
     * @param url - skąd ściąga
     * @param fileName - nazwa pliku
     * @throws IOException


    private static void download(String url, String fileName) throws IOException {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            Files.copy(in,Paths.get(fileName));
        }
    }
    */
//C:\Users\Szymon\IdeaProjects\Rozdzial2\src\Homeworks

    /**
     *  Downloading and saving file
     * @param fileURL- skąd ściagasz
     * @param saveDir- gdzie zapisać
     * @param fileName- jak ma się nazywać plik
     * @throws IOException
     */
    private static void download(String fileURL, String saveDir,String fileName)
        throws IOException {
    URL url = new URL(fileURL);
    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
    int responseCode = httpConn.getResponseCode();

    // always check HTTP response code first
    if (responseCode == HttpURLConnection.HTTP_OK) {
        String contentType = httpConn.getContentType();
        int contentLength = httpConn.getContentLength();


        System.out.println("Content-Type = " + contentType);
        System.out.println("Content-Length = " + contentLength);
        System.out.println("fileName = " + fileName);

        // opens input stream from the HTTP connection
        InputStream inputStream = httpConn.getInputStream();
        String saveFilePath = saveDir + File.separator + fileName;

        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);

        int bytesRead = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        System.out.println("File downloaded");
    } else {
        System.out.println("No file to download. Server replied HTTP code: " + responseCode);
    }
    httpConn.disconnect();
}

    private static int getNumberLines(File aFile) {
        int numLines = 0;
        try {

            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                String line = null;

                while (( line = input.readLine()) != null){ //ReadLine returns the contents of the next line, and returns null at the end of the file.
                    numLines++;
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return numLines;
    }

    private String readLine(int lineNumber, File aFile) {
        String lineText = "";
        try {

            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
                for(int count = 0; count < lineNumber; count++) {
                    input.readLine();  //ReadLine returns the contents of the next line, and returns null at the end of the file.
                }
                lineText = input.readLine();
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return lineText;
    }

    /**
     * Na końcu każdego wiersza dodaje nową pozycję mówiącą o precentowej różnicy z pozycji 2 i 5
     * Jest to jednocześnie procentowy wynik firmy w okresie opisanym w wierszu
     */
    private void doSomethingToStrings() {
        lines[0]=lines[0]+", Procentowa zmiana";
        for(int i=1; i<numLines; i++) {
            String[] elems = lines[i].split(",");
            double wynik = ((valueOf(elems[4]) - valueOf(elems[1])) / valueOf(elems[1]));
            wynik*=10000;
            wynik=Math.round(wynik);
            wynik/=100;
            String[] elemsOut = new String[elems.length + 1];
            for (int j = 0; j < elems.length; j++) {
                elemsOut[j] = elems[j];
            }
            elemsOut[elems.length] = Double.toString(wynik);
            elemsOut[elems.length]= elemsOut[elems.length]+"%";
            lines[i]= String.join(",", elemsOut);
        }

    }

    private void writeFile(File aFile) throws FileNotFoundException, IOException {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            throw new FileNotFoundException ("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }

        BufferedWriter output = new BufferedWriter(new FileWriter(aFile));
        try {
            for(int count = 0; count < numLines; count++) {
                output.write(lines[count]);
                if(count != numLines-1) {// This makes sure that an extra new line is not inserted at the end of the file
                    output.newLine();
                }

            }

        }
        finally {
            output.close();
        }
    }


    private SciagnijIPrzerabiaj(File aFile){
        yourFile= aFile;
        numLines = getNumberLines(yourFile);
        lines = new String[numLines];//here we set the size of the array to be the same as the number of lines in the file
        for(int count = 0; count < numLines; count++) {
            lines[count] = readLine(count,yourFile);//here we set each string in the array to be each new line of the file
        }

    }
    public static void main(String[] args) throws IOException {
        download("https://stooq.pl/q/d/l/?s=msft.us&i=d","C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper","MSFT.csv");
        File file1= new File("C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper\\MSFT.csv");
        SciagnijIPrzerabiaj Obiekt1 = new SciagnijIPrzerabiaj(file1);
        Obiekt1.doSomethingToStrings();
        Obiekt1.writeFile(file1);

        download("https://stooq.pl/q/d/l/?s=amd.us&i=d","C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper","AMD.csv");
        File file2= new File("C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper\\AMD.csv");
        SciagnijIPrzerabiaj Obiekt2 = new SciagnijIPrzerabiaj(file2);
        Obiekt2.doSomethingToStrings();
        Obiekt2.writeFile(file2);

        download("https://stooq.pl/q/d/l/?s=eng&i=d","C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper","ENG.csv");
        File file3= new File("C:\\Users\\Szymon\\IdeaProjects\\Rozdzial2\\src\\Homeworks\\Homework3FilesOper\\ENG.csv");
        SciagnijIPrzerabiaj Obiekt3 = new SciagnijIPrzerabiaj(file3);
        Obiekt3.doSomethingToStrings();
        Obiekt3.writeFile(file3);


    }
}
