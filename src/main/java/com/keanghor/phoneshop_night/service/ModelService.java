package com.keanghor.phoneshop_night.service;
import com.keanghor.phoneshop_night.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrandId(Long brandId);
    Model getById(Long id);
}
