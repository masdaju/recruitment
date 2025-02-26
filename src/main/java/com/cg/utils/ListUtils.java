package com.cg.utils;

import com.cg.entity.Resources;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<List<Long>> compare(List<Long> resourceList, List<Long> resId){
        List<Long> differentInResourceList = new ArrayList<>();
        List<Long> differentInResId = new ArrayList<>();

        for (Long elementInResourceList : resourceList) {
            if (!resId.contains(elementInResourceList)) {
                differentInResourceList.add(elementInResourceList);
            }
        }
        for (Long elementInResId : resId) {
            if (!resourceList.contains(elementInResId)) {
                differentInResId.add(elementInResId);
            }
        }
        return List.of(differentInResourceList, differentInResId);
    }

    public static List<Long> findAllChildren(List<Resources> treeList, Long targetId) {
        List<Long> result = new ArrayList<>();
        // 添加目标节点的ID
        result.add(targetId);
        // 递归查找子节点
        treeList.forEach(node -> {
            if (node.getPid().equals(targetId)) {
                result.addAll(findAllChildren(treeList, node.getId()));
            }
        });

        return result;
    }


}
