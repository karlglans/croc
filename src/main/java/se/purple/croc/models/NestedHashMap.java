package se.purple.croc.models;

import lombok.var;

import java.util.HashMap;
import java.util.Map;

// (key1, key2) => count
public class NestedHashMap<K1, K2> {
	HashMap<K1, HashMap<K2, Integer>> nestedHashMap;

	public NestedHashMap() {
		nestedHashMap = new HashMap<>();
	}

	public int addCount(K1 key1, K2 key2) {
		var secondCollection = nestedHashMap.get(key1);
		if (!nestedHashMap.containsKey(key1)) {
			var create2ndCollection = new HashMap<K2, Integer>();
			nestedHashMap.put(key1, create2ndCollection);
			create2ndCollection.put(key2, 1); // adding first count
			return 1;
		}
		Integer count = secondCollection.get(key2);
		if (count == null) {
			secondCollection.put(key2, 1); // adding first count
			return 1;
		}
		count += 1;
		secondCollection.put(key2, count);
		return count;
	}

	public int findCount(K1 key1, K2 key2) {
		var firstCollection = nestedHashMap.get(key1);
		if (firstCollection.isEmpty()) {
			return 0;
		}
		var secondCollection = firstCollection.get(key2);
		if (secondCollection == null) {
			return 0;
		}
		return secondCollection.intValue();
	}

	public Map<K2, Integer> getInnerMap(K1 key1){
		return nestedHashMap.get(key1);
	}
}
