package pobj.tme5.test;

import java.util.List;

import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {
	
	public static void main(String[] args) {
		System.out.println("Parse le texte du fichier ParserTest.txt : ");
		try {
			MultiSet<String> parser = MultiSetParser.parse("data/ParserTest.txt");
			List<String> l = parser.elements();
	
			for (int i=0; i< parser.elements().size(); i++) {
				String e = l.get(i);
				System.out.println(e + ":" + parser.count(e));
			}
		} catch(InvalidMultiSetFormat i) {
			i.getMessage();
		}
	}
}
