package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class WordCount {
	//MultiSet<String> ms= new HashMultiSet<String>();

	
	public static void wordcount(MultiSet<String> ms) throws IOException {
		//String file = "C:\\Users\\Zahra\\eclipse-workspace\\multiset\\src\\pobj\\tme4\\Monfichier.txt";
		String file = "data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		while((line= br.readLine())!=null) {
			for (String word : line.split("\\P{L}+")) { 
				if (word.equals("")) continue;
				ms.add(word);
			}
		}
		br.close();
		
		List<String> liste = ms.elements();
		
		
		Collections.sort(liste,(HashMultiSet<String>)ms);
		
		System.out.println("Les 10 mots les plus utilisés");
		for(int i =0; i<10 ; i++) {
			System.out.println("sorted list ::"+liste.get(i));
		}
		
	}
	@Test
	public void Test() {
		MultiSet<String>ms= new HashMultiSet<>();
		try {
			WordCount.wordcount(ms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}