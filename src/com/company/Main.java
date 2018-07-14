package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Main {
    static int m=0;
    static int n = 0;
    public static void main(String[] args) {
	// write your code here
        System.out.println(permutation("AABC"));
        System.out.println(permutationDistinct("AABC"));
        System.out.println(m + ","+ n);

    }

    private static TreeMap<Character, Integer> createMap(String string) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i<string.length(); i++) {
            map.put(string.charAt(i), map.getOrDefault(string.charAt(i),0)+1);
        }
        return map;
    }


    public static List<String> permutation(String string) {
        if (string == null || string.length() == 0){
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        TreeMap<Character, Integer> map = createMap(string);

        char[] result = new char[string.length()];
        char[] letters = new char[map.size()];
        int[] count = new int[map.size()];

        populateArrays(letters, count, map);

        permuteHelperWithFullLength(letters, count, result,0, list, new StringBuilder());

        return list;

    }

    public static List<String> permutationDistinct(String string) {
        if (string == null || string.length() == 0){
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        TreeMap<Character, Integer> map = createMap(string);

        char[] result = new char[string.length()];
        char[] letters = new char[map.size()];
        int[] count = new int[map.size()];

        populateArrays(letters, count, map);

        permuteHelperDistinct(letters, count, result,0, list, 0, new StringBuilder());

        return list;

    }

    private static void populateArrays(char[] letters, int[] count, TreeMap<Character,Integer> map) {
        int i = 0;
        for (char c: map.keySet()) {
            letters[i] = c;
            count[i] = map.get(c);
            i++;
        }
    }

    private static void permuteHelperWithFullLength(char[] letters, int[] count, char[] result, int level, List<String> list, StringBuilder sb) {


        m++;
        if (level == result.length) {
            list.add(sb.toString());
            return;
        }


        for (int i = 0; i<letters.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            sb.append(letters[i]);
            count[i]--;
            permuteHelperWithFullLength(letters, count, result,level+1, list, sb);
            count[i]++;
            sb.deleteCharAt(sb.length()-1);
        }

    }
    private static void permuteHelperDistinct(char[] letters, int[] count, char[] result, int level, List<String> list, int pos, StringBuilder sb) {
        list.add(sb.toString());
        if (level == result.length) {
            return;
        }
n++;
        for (int i = pos; i<letters.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            sb.append(letters[i]);
            count[i]--;
            permuteHelperDistinct(letters, count, result,level+1, list, i, sb);
            count[i]++;
            sb.deleteCharAt(sb.length()-1);
        }

    }



}
