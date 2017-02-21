package com.kd.commons.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CollectionUtils {


	/**
	 * 反转map
	 * @param map
	 * @return
	 */
	public static <T1,T2> Map<T2,T1> reverse(Map<T1,T2> map){
		Map<T2, T1> mapret = new HashMap<T2, T1>();
		Set<Entry<T1, T2>> entrySet = map.entrySet();
		for (Entry<T1, T2> entry : entrySet) {
			mapret.put(entry.getValue(), entry.getKey());
		}
		return mapret;
	}

	public static <T> T getRandom(
			List<T> list) {
		int length = list.size();
		int index = 0;
		if (length > 1) {
			double random = Math.random();
			index = (int) (random * length);
		}
		return list.get(index);
	}

	public static <T> boolean contains(T[] args, T expected) {
		for (T t : args) {
			if (t.equals(expected)){
				return true;
			}
		}
		return false;
	}

	public static <T> boolean contains(Collection<T> args, T expected) {
		for (T t : args) {

			if (t.equals(expected)){
				return true;
			}
		}
		return false;
	}

	public static <T> T get(Map<String, T> map, String key,T defaultValue) {
		if (!map.containsKey(key)){
			map.put(key, defaultValue);
		}
		return (T) map.get(key);
	}

	/**
	 * 按map中的value排序,默认为升序
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map sortByValue(Map<?, ?> map) {
	   return sortByValue(map,false);
	}

	/**
	 * 按map中的value排序
	 * @param map
	 * @param reverse 是否降序
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortByValue(Map map, final boolean reverse) {
	        List list = new LinkedList(map.entrySet());
	        Collections .sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	                if (reverse) {
	                    return -((Comparable) ((Map .Entry)o1).getValue())
	                            .compareTo(((Map .Entry)o2).getValue());
	                }
	                return ((Comparable) ((Map .Entry)o1).getValue())
	                        .compareTo(((Map .Entry)o2).getValue());
	            }
	        });

	        Map result = new LinkedHashMap();
	        for (Iterator it = list.iterator(); it.hasNext();) {
	            Map.Entry entry = (Map.Entry) it.next();
	           result.put(entry.getKey(), entry.getValue());
	        }
	        return result;
	}

	public static List<String> sortByStringLength(List<String> urlList) {
		Comparator<String> c = new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		};
		Collections.sort(urlList, c);
		return urlList;
	}

	@SuppressWarnings("rawtypes")
	public static String join(Collection contents, String str) {
		StringBuffer sb = new StringBuffer();
		if (contents!=null){
			for (Object obj : contents) {
				sb.append(obj).append(str);
			}
		}
		return sb.toString();
	}



}
