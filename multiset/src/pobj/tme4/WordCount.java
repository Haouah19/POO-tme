package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {
	public static void main(String [] args ) {
		System.out.println("HashMultiSet");
		System.out.println("------------");
		MultiSet<String> ms1 = new HashMultiSet<String>();
		Chrono chrono1 = new Chrono();
		wordcount(ms1);
		chrono1.stop();

		
		System.out.println("\nNaiveMultiSet");
		System.out.println("------------");
		NaiveMultiSet<String> ms2 = new NaiveMultiSet<>();
		Chrono chrono2 = new Chrono();
		wordcount(ms2);
		chrono2.stop();

		
	}
	
	
	public static void wordcount(MultiSet<String> ms){
		
		try {
		// Lecture du fichier
		String file = "data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		while((line = br.readLine())!=null) {
			for(String word : line.split("\\P{L}+")) {
				if(word.equals(""))
					continue;
				
				// ajout du mot dans la liste ms
				ms.add(word);
				}
			}
		br.close();
		
		class MultiSetComparator<T> implements Comparator<T> {
			
			MultiSet<T> ms;
			
			public MultiSetComparator(MultiSet<T> ms) {
				this.ms = ms;
			}

			@Override
			public int compare(T o1, T o2) {
				return Integer.compare(ms.count(o2), ms.count(o1));
			}
			
		}
		
		// création d'un comparator avec la liste ms
		Comparator<String> compare = new MultiSetComparator<String>(ms);
		
		// Récupération des éléments dans une liste 
		List<String> trie = ms.elements();
		
		// tri de trie
		trie.sort(compare);

		// Affichage des 10 premiers 
		for(int i=0; i<10 ; i++) {
			String cle = trie.get(i);
			System.out.println(cle + " : " + ms.count(cle));
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
