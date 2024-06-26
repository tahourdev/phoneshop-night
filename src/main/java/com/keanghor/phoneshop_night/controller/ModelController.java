package com.keanghor.phoneshop_night.controller;


import com.keanghor.phoneshop_night.dto.ModelDTO;
import com.keanghor.phoneshop_night.entity.Model;
import com.keanghor.phoneshop_night.mapper.ModelMapper;
import com.keanghor.phoneshop_night.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;

//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> createModel(@RequestBody ModelDTO modelDTO){
        Model model = modelMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelMapper.toModelDTO(model));
    }
}
