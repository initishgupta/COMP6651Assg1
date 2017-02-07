import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// Nitish Gupta (27244908)

public class UnscrambleWords {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String inputFileName = null;
        System.out.print("Enter Input File Name: ");
        try{
        	inputFileName = br.readLine();	
        }
        catch(IOException exception)
        {
         System.out.println("Exception Caught: "+exception.getMessage());	
        }
        PrintWriter writer = new PrintWriter(inputFileName+"_Output.txt", "UTF-8");
		final long startTime = System.nanoTime();
		int flagForDictionary = 0;
		String wordScanned;
		ArrayList<String> mydictionary = new ArrayList<String>();
		ArrayList<String> mytestAnagrams = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new File("./InputFiles/"+inputFileName+".txt"));
			while (sc.hasNext()) {
				wordScanned = sc.next();
				if (wordScanned.equals("-----")) {
					flagForDictionary++;
				}
				if (flagForDictionary == 0) {
					mydictionary.add(wordScanned);
				}
				if (flagForDictionary == 1 && !wordScanned.equals("-----")) {
					mytestAnagrams.add(wordScanned);
				}

			}
			sc.close();
		}

		catch (Exception e) {
			System.out.println("Exception Caught" + e.getMessage());
		}
		DictionaryCreator dictionaryCreator = new DictionaryCreator(mydictionary);
		Iterator<String> myIterator = mytestAnagrams.iterator();
		while (myIterator.hasNext()) {
			Collection<String> anagramFinder = dictionaryCreator.getAnagrams(myIterator.next());
			if (anagramFinder.isEmpty()) {
				writer.println("No Answer Found");
				System.out.println("No Answer Found");
				writer.println("-------------------");
				System.out.println("-------------------");
			} else {
				List<String> sortedAnagrams = new ArrayList<String>(anagramFinder);
				Collections.sort(sortedAnagrams);
				for (String anagramsFound : sortedAnagrams) {
					writer.println("" + anagramsFound);
					System.out.println("" + anagramsFound);
				}
				writer.println("-------------------");
				System.out.println("-------------------");
			}

		}

		final long endTime = System.nanoTime();
		
		writer.close();

		System.out.println("Program Took: " + ((endTime - startTime) / 1000000) + " milliseconds");

	}
}
