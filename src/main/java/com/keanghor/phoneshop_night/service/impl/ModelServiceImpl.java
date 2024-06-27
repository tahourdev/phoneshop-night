package com.keanghor.phoneshop_night.service.impl;

import com.keanghor.phoneshop_night.entity.Model;
import com.keanghor.phoneshop_night.repository.ModelRepository;
import com.keanghor.phoneshop_night.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    //Inject dependency using constructor
    private  final ModelRepository modelRepository;
//    private final ModelMapper modelMapper;

    @Override
    public Model save(Model model) {
//        Model model = modelMapper.toModel(modelDTO);
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getByBrandId(Integer brandId) {
        return modelRepository.findByBrandId(brandId);
    }
}
