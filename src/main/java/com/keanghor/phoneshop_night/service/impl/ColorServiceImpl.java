package com.keanghor.phoneshop_night.service.impl;

import com.keanghor.phoneshop_night.entity.Color;
import com.keanghor.phoneshop_night.exception.ResourceNotFoundException;
import com.keanghor.phoneshop_night.repository.ColorRepository;
import com.keanghor.phoneshop_night.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Color", id)
        );
    }
}
