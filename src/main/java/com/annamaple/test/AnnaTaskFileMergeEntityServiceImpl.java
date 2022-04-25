package com.annamaple.test;

import cn.hutool.core.lang.Console;
import lombok.SneakyThrows;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author xionglei
 * @create 2022-04-22 17:43
 */
//@Service
public class AnnaTaskFileMergeEntityServiceImpl implements ITaskFileMergeEntityService {

    @Override
    public Map<String, List<String>> getMergeEntityList(String singleTaskFlag, String singleFileFlag, String singlePeriod, Map<String, String> entityParentList) throws Exception {
        if (entityParentList == null || entityParentList.isEmpty()) {
            return null;
        }
        // 生成一个新的map(key为parentCode, 且只记录parentCode为乡镇级别的, value为codeList)
        // 全国乡镇区划数量 http://xzqh.mca.gov.cn/statistics/2020.html  1 << 16 = 65526
        int mapSize = 1 << 16;
        Map<String, List<String>> resMap = new HashMap<>(mapSize);
        entityParentList.forEach((code, parentCode) -> {
            if (isTownCode(parentCode)) {
                List<String> list = resMap.computeIfAbsent(parentCode, k -> new LinkedList<>());
                list.add(code);
            }
        });
        return resMap;
    }

    /**
     * 判断当前区划代码是否是乡镇代码
     *
     * @param code 区划代码
     * @return true 是 false 否
     */
    private boolean isTownCode(String code) {
        // todo-lei fixme: 进行判断
        return code.charAt(code.length() - 2) == '0' && code.charAt(code.length() - 1) != '0';
    }
    
    
    // test
    @SneakyThrows
    public static void main(String[] args) {
        Map<String, String> map = createCodeMap();
        ITaskFileMergeEntityService service = new AnnaTaskFileMergeEntityServiceImpl();
        Map<String, List<String>> resMap = service.getMergeEntityList("", "", "", map);
        resMap.forEach((k, v) -> Console.log("townCode:【{}】, childList：{}", k, v));
    }


    private static Map<String, String> createCodeMap() {
        Map<String, String> map = new HashMap<>();
        map.put("5134361021", "513436102");
        map.put("5134361022", "513436102");
        map.put("5134361023", "513436102");
        map.put("5134361024", "513436102");
        map.put("5134361025", "513436102");
        map.put("5134361026", "513436102");
        map.put("513436102", "513436100");
        map.put("513436100", "513436000");
        map.put("513436000", "513430000");
        map.put("5134361031", "513436103");
        map.put("5134361032", "513436103");
        map.put("5134361033", "513436103");
        map.put("5134361034", "513436103");
        map.put("5134361035", "513436103");
        return map;
    }
}
