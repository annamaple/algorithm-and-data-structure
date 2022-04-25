package com.annamaple.test;

import java.util.List;
import java.util.Map;

/**
 * @author xionglei
 * @create 2022-04-22 17:41
 */
public interface ITaskFileMergeEntityService {
    
    Map<String, List<String>> getMergeEntityList(String singleTaskFlag, String singleFileFlag, String singlePeriod, Map<String, String> entityParentList) throws Exception;
}
