package com.concurrence.app.common.domain;


import com.concurrence.app.common.constants.NumberConstants;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chenqw
 * @date 2022/11/2 16:34
 */
@Getter
@NoArgsConstructor
public class PageRequest implements Serializable {

    private Integer pageIndex;
    private Integer pageSize;

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = NumberConstants.ONE;
        }
        this.pageIndex = pageIndex;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            pageSize = NumberConstants.TEN;
        }
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = NumberConstants.ONE;
        }
        return pageIndex;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            pageSize = NumberConstants.TEN;
        }
        return pageSize;
    }
}
