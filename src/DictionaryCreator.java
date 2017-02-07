import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class DictionaryCreator {
	private Map<String, Collection<String>> customizedDictionary;

	public DictionaryCreator(List<String> dictionary) {
		this.customizedDictionary = createDictionary(dictionary);
	}

	public Collection<String> getAnagrams(String word) {
		Collection<String> valuesFound = Collections.emptyList();
		if (customizedDictionary.containsKey(sortGivenString(word))) {
			valuesFound = customizedDictionary.get(sortGivenString(word));
			return valuesFound;
		}
		return valuesFound;

	}

	public boolean checkIfKeysExists(String word) {
		if (customizedDictionary.containsKey(sortGivenString(word))) {
			return true;
		}
		return false;
	}

	private Map<String, Collection<String>> createDictionary(List<String> dictionary) {

		Multimap<String, String> customizedDictionary = HashMultimap.create();

		for (String word : dictionary) {
			customizedDictionary.put(sortGivenString(word), word);
		}

		return customizedDictionary.asMap();
	}

	public String sortGivenString(String word) {
		List<Character> sortedCharacters = new ArrayList<Character>();
		for (char c : word.toCharArray()) {
			sortedCharacters.add(c);
		}
		Collections.sort(sortedCharacters);
		StringBuilder builder = new StringBuilder();
		for (Character character : sortedCharacters) {
			builder.append(character);
		}

		return builder.toString();
	}
}
