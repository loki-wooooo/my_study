package io.github.lokiwooooo.hierarchy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class HierarchyBuilder {

    /**
     * node - class instance
     * nodesByLevel - list가 groupping 된 데이터
     * maxLevel - 최대 계층의 level
     */
    public <T extends HierarchyNode<T>> T buildHierarchy(
            final T node,
            final Map<Integer, List<T>> nodesByLevel,
            final Integer maxLevel
    ) {
        int currentLevel = Integer.parseInt(node.getLevel());
        if (currentLevel >= maxLevel) {
            node.setChildren(new ArrayList<>());
            return node;
        }

        int nextLevel = currentLevel + 1;
        List<T> children = nodesByLevel
                .getOrDefault(nextLevel, new ArrayList<>())
                .stream()
                .filter(child -> node.getId().equals(child.getParentId()))
                .map(child -> {
                    // parentId가 null이거나 잘못된 경우 부모의 id로 세팅
                    if (child.getParentId() == null || !child.getParentId().equals(node.getId())) {
                        child.setParentId(node.getId());
                    }
                    // 재귀적으로 자식 트리 빌드
                    return buildHierarchy(child, nodesByLevel, maxLevel);
                })
                .toList();

        node.setChildren(children);
        return node;
    }

}