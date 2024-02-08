
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tail = str.substring(1);
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		String lWord1=word1.toLowerCase();
		String lWord2=word2.toLowerCase();
		if (lWord2.length()==0)
			return lWord1.length();
		if (lWord1.length()==0)
			return lWord2.length();
		if (lWord1.charAt(0)==lWord2.charAt(0))
			return levenshtein(tail(lWord1),tail(lWord2));
		else
		return 1+(Math.min(Math.min(levenshtein(tail(lWord1), lWord2),levenshtein(lWord1,tail(lWord2))),levenshtein(tail(lWord1),tail(lWord2))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In("dictionary.txt");
			for(int i=0;i<3000;i++){
				dictionary[i]=in.readLine();
			}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minTh = 100;
		word = word.toLowerCase();
		String match = word;
		for (int i=0;i<dictionary.length;i++){
			if (levenshtein(dictionary[i], word)<minTh&&levenshtein(dictionary[i], word)<=threshold) {
				match=dictionary[i];
				minTh=levenshtein(dictionary[i], word);
			}
		}
	return match;
	}
	
}
