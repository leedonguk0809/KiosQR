package org.zerock.wcup.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageRequestDTO {

    private int page = 1;

    private int size = 10;

    private String type;

    private String keyword;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size <= 10 ? 10 : size > 100 ? 100 :size;
    }

    public void setType(String type) {

        if(type == null || type.trim().length() == 0){
            this.type = null;
            return;
        }
        this.type = type;
    }

    public void setKeyword(String keyword) {

        if(keyword == null || keyword.trim().length() == 0){
            this.keyword = null;
            return;
        }

        this.keyword = keyword;
    }
}
