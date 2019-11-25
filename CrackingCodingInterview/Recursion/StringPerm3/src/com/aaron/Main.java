package com.aaron;

// while my own implementation works, this implementation is clearly better in mutliple ways. key learning below:
// 1) recursion is not only about break big problems into smaller problems, but also about convert big uncertainty to smaller uncertainty
//          through recursion, you can make assumptions about uncertain variables and convert them into specifics.
// 2) the usage of "prefix" - related to the previous one, you can try to do compute at every recursion layer. this makes more sense when you break big
//          problem down to smaller problems. The other strategy (that is more efficient in this case), is to defer the compute needed to later recursion layers
//          through the use of passing down arguments. In a sense, instead of reducing the problem to sovle layer by layer (and solving it at every recurison),
//          you keep making assumptions and postpone the compute to the end where you have no uncertainty. with that clarity your implementation might be
//          extremly simple and straightforward
// 3) the usage of "remaining" - you can carry through additional information through passing down arguments, which might save you extra compute needed to
//          generate the value in later recursion
// 4) there are multiple ways to break down the problem. my selection is a2b2c2-> a2 + b2c2, while the working one is a2b2c2 -> a + a2b2c2, b+, c+...
//          there is no rigth or wrong. you have to implement and analyze to see which one is more efficient
// 5) use of HashMap direct other than the unnatural ArrayList<Pair<key, value>>

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static HashMap<Character, Integer> genCharCount (String str) {
        HashMap<Character, Integer> hashmap = new HashMap<>();
        for(char c: str.toCharArray()) {
            if(!hashmap.containsKey(c)) {
                hashmap.put(c, 1);
            } else {
                int val = hashmap.get(c);
                val ++;
                hashmap.put(c, val);
            }
        }
        return hashmap;
    }

    private static void _getPerms(HashMap<Character, Integer> map, String prefix, int remaining, ArrayList<String> result) {
        if(remaining == 0) {
            result.add(prefix);
            return;
        }

        for(Character c: map.keySet()) {
            int count = map.get(c);
            if(count > 0) {
                map.put(c, count - 1);
                _getPerms(map, prefix + c, remaining - 1, result);
                map.put(c, count);
            }
        }
    }

    public static ArrayList<String> getPerms(String str) {
        HashMap<Character, Integer> map = genCharCount(str);
        ArrayList<String> result = new ArrayList<>();
        _getPerms(map, "", str.length(), result);
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> results = getPerms("aabbcd");
        System.out.println(results.size());
        System.out.println(results);


    }
}
