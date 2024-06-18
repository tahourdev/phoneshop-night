package com.keanghor.phoneshop_night.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {
    private List<?> lists;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page){
        this.lists = page.getContent();
        this.pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPage(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .numberOfElement(page.getNumberOfElements())
                .build();
    }
}
