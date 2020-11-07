package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	
	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
		MultiSet<String> multiset = new HashMultiSet<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			for (String line = br.readLine(); line!= null; line = br.readLine()) {
				String [] parts = line.split(":");
				multiset.add(parts[0], Integer.decode(parts[1].trim()));
				
			}
			br.close();
			
	
		}catch(FileNotFoundException f){
			
			throw new InvalidMultiSetFormat("fichier introuvable",
					f.getCause());
			
		}
		catch(NumberFormatException n) {
			
			throw new InvalidMultiSetFormat("caractère « : » suivi d’une chaîne ne représentant pas un entier strictement positif",
					n.getCause());
			
		}catch(ArrayIndexOutOfBoundsException i){
			
			throw new InvalidMultiSetFormat("ligne ne contenant pas de caractère « : »",
					i.getCause()); 
		
		
		}catch (IOException e) {
			
			throw new InvalidMultiSetFormat("erreurd’entrées-sortie", e.getCause());
			
		}
		
		return multiset;
		
		
		
	}
}
