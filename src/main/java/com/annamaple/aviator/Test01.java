package com.annamaple.aviator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xionglei
 * @create 2022-06-15 16:40
 */
public class Test01 {
    public static void main(String[] args) {
        String aaa  = "ZT_ORG_FXCWPF[SFBS]='0101' AND ZT_ORG_FXCWPF[FX_DWLX]='02'";
        String[] a = aaa.split("AND");
        List<Map<String,String>> filterList  = new ArrayList<>();
        for (String s : a) {
            Map<String,String> map = new HashMap<>();
            map.put(s.substring(s.indexOf('[') + 1,s.indexOf(']')),s.substring(s.indexOf("'") + 1,s.lastIndexOf("'")));
            filterList.add(map);
        }
    }
}
