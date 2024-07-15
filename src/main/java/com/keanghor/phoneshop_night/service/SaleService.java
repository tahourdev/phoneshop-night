package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.dto.SaleDTO;

import java.time.LocalDateTime;

public interface SaleService {
    void sell(SaleDTO saleDTO);
}
