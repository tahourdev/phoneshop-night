package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.SaleDTO;
import com.keanghor.phoneshop_night.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //already built-in response body
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO){
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }
}
