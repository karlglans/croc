package se.purple.croc.models;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class NestedHashMapTests {

	@Test
	public void addCount() {
		NestedHashMap<String, String> nestedHashMap = new NestedHashMap<>();

		int count = nestedHashMap.addCount("aa", "bb");
		assertEquals(1, count);
	}

	@Test
	public void addCountTwice() {
		NestedHashMap<String, String> nestedHashMap = new NestedHashMap<>();
		nestedHashMap.addCount("aa", "bb");
		int count2nd = nestedHashMap.addCount("aa", "bb");
		assertEquals(2, count2nd);
	}

	@Test
	public void addDiffKey2() {
		NestedHashMap<String, String> nestedHashMap = new NestedHashMap<>();
		nestedHashMap.addCount("aa", "bb");
		int count2nd = nestedHashMap.addCount("aa", "bbbb");
		assertEquals(1, count2nd);
	}

	@Test
	public void findCount() {
		NestedHashMap<String, String> nestedHashMap = new NestedHashMap<>();
		nestedHashMap.addCount("aa", "bb");
		nestedHashMap.addCount("aa", "bb");
		int foundCount = nestedHashMap.findCount("aa", "bb");
		assertEquals(2, foundCount);
	}

	@Test
	public void getInnerMap() {
		NestedHashMap<String, String> nestedHashMap = new NestedHashMap<>();
		String key = "aa";
		nestedHashMap.addCount("aaaa", "aaaa1");
		nestedHashMap.addCount(key, "aa1"); // item 1
		nestedHashMap.addCount("aaaa", "aaaa2");
		nestedHashMap.addCount(key, "aa2"); // item 2
		nestedHashMap.addCount(key, "aa2");	// also item 2
		nestedHashMap.addCount(key, "aa2"); // also item 2
		Map<String, Integer> innerMap = nestedHashMap.getInnerMap(key);
		assertEquals(2, innerMap.size());
		assertEquals(1, innerMap.get("aa1").intValue());
		assertEquals(3, innerMap.get("aa2").intValue());
	}
}
