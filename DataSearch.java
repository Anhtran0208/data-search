/*
 * The DataSearch will read information from data.txt and seek.txt files and then sort the data
 * alphabetically and then perform a recursive binary search to determine if the given seek values exist
 * in the data. The program will then write to an output file which seek values were found and which were not
 * @author Anh Tran
 * Assignment 5
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class DataSearch{

    /*
     * readStringFromFile method to read seek.txt file and put it into the array
     * @param fileName - the file that needs to be read
     * @return the array of all data in the seek.txt file
     */
    public static String[] readStringFromFile (String fileName) throws FileNotFoundException{
        ArrayList<String> titles = new ArrayList<>();
        File inFile = new File(fileName);
        Scanner dataIn = new Scanner(inFile);
        while (dataIn.hasNextLine()){
            titles.add(dataIn.nextLine());
        }
        String[] stringArray = new String[titles.size()];
        return titles.toArray(stringArray);
    }

    /*
     * readBookFromFile method to read the data.txt file
     * @param fileName - the file that needs to be read 
     * @return a Book array that contains all the data in the file
     */
    public static Book[] readBookFromFile(String fileName) throws FileNotFoundException{
        ArrayList<Book> bookList = new ArrayList<>();
        File file = new File(fileName);
        Scanner data = new Scanner(file);
        while (data.hasNextLine()){
            String[] bookVals = data.nextLine().split(",");
            String title = bookVals[0];
            String author = bookVals[1];
            int pageCount = Integer.parseInt(bookVals[2]);
            int yearPublished = Integer.parseInt(bookVals[3]);
            String genres = bookVals[4];
            bookList.add(new Book(title, author, pageCount, yearPublished, genres));
        }
        Book[] booksArray = new Book[bookList.size()];
        return bookList.toArray(booksArray);
    }

    public static void main(String[] args) throws FileNotFoundException{
        //read the seek.txt file
        String[] titlesArray = readStringFromFile(args[0]);

        //Read the data.txt file 
        Book[] booksArray = readBookFromFile(args[1]);
        
        //sort the list of Book objects according to the book title 
        for (int i = 0; i < booksArray.length - 1; i++){
            for (int j = 0; j < booksArray.length - i - 1; j++){
                if (booksArray[j].getTitle().compareTo(booksArray[j+1].getTitle()) > 0){
                    Book temp = booksArray[j];
                    booksArray[j] = booksArray[j+1];
                    booksArray[j+1] = temp;
                }
            }
        }
        
        // create a found array and update the record
        boolean[] found = new boolean[titlesArray.length];
        for (int i = 0; i < found.length; i++){
            found[i] = binarySearch(booksArray, titlesArray[i], 0, booksArray.length - 1);
        }   

        PrintWriter outs = new PrintWriter(new FileOutputStream("output.txt"));
        for (int i = 0; i < found.length; i++){
            if (found[i] == true){
                outs.println(titlesArray[i] + ": Yes");
            }
            else{
                outs.println(titlesArray[i] + ": No");
            }
        }
        outs.close();
    }

    /*
     * binarySearch method to search for the title of each book 
     * @param Book[] booksArray - the array we will look at to search the value 
     * @param data - the data that needs to be looking for 
     * @param start  - the start index
     * @param end - the end index 
     */
    private static boolean binarySearch(Book[] booksArray, String data, int start, int end) {
        int middle = (start + end)/2;
        //base case 
        if (end < start){
            return false;
        }

        int compare = data.compareTo(booksArray[middle].getTitle());
        if (compare == 0){
            return true;
        }
        else if (compare < 0){
            return binarySearch(booksArray, data, start, middle - 1);
        }
        else{
            return binarySearch(booksArray, data, middle + 1, end);
        }
    }
}