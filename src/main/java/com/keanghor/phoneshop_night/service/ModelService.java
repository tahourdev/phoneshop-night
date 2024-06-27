package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.dto.ModelDTO;
import com.keanghor.phoneshop_night.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrandId(Integer brandId);
}
