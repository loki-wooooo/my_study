package io.github.lokiwooooo.chapter04.entity;


public class CarInventory {
    Integer totalCount;

    Integer expectedTotalCount;

    public CarInventory(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setExpectedTotalCount(Integer expectedTotalCount) {
        this.expectedTotalCount = expectedTotalCount;
    }

    public Integer getExpectedTotalCount(Integer count) {
        return expectedTotalCount + count;
    }
}
