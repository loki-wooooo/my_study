package io.github.lokiwooooo.hierarchy;

import java.util.List;

public interface HierarchyNode<T> {
    String getId();

    String getLevel();

    String getParentId();

    T getParent();

    List<T> getChildren();

    void setParentId(String parentId);

    void setChildren(List<T> children);
}
