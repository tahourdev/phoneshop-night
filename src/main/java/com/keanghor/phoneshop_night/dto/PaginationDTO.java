package com.keanghor.phoneshop_night.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
    private long totalElement;
    private long numberOfElement;
    private boolean first;
    private boolean last;
    private boolean empty;
}
