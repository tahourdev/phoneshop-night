package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.entity.Color;

public interface ColorService {
    Color create(Color color);
    Color getById(Long id);
}
