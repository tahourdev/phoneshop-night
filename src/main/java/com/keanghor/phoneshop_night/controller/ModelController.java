package com.keanghor.phoneshop_night.controller;


import com.keanghor.phoneshop_night.dto.ModelDTO;
import com.keanghor.phoneshop_night.entity.Model;
import com.keanghor.phoneshop_night.mapper.ModelEntityMapper;
import com.keanghor.phoneshop_night.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @PostMapping
    public ResponseEntity<?> createModel(@RequestBody ModelDTO modelDTO){
        Model model = modelEntityMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelEntityMapper.toModelDTO(model));
    }


}
