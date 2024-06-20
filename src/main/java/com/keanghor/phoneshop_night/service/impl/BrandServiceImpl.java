package com.keanghor.phoneshop_night.service.impl;
import com.keanghor.phoneshop_night.exception.ResourceNotFoundException;
import com.keanghor.phoneshop_night.spec.BrandFilter;
import com.keanghor.phoneshop_night.spec.BrandSpec;
import com.keanghor.phoneshop_night.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.repository.BrandRepository;
import com.keanghor.phoneshop_night.service.BrandService;

import java.util.List;
import java.util.Map;

@Service // Annotates this class as a service component in Spring's context
public class BrandServiceImpl implements BrandService {

    @Autowired // Injects the BrandRepository dependency
    private BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand){
        // Saves the provided Brand object to the database
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
        // Retrieves a Brand by its ID; throws an exception if not found
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", id));
    }

    @Override
    public Brand update(Integer id, Brand brandUpdate) {
        // Updates the Brand with the provided ID using the data in brandUpdate
        Brand brand = getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }

    /*@Override
    public List<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if(params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);

        }
        if(params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }
        BrandSpec brandSpec = new BrandSpec(brandFilter);
        Page<Brand> brands = brandRepository.findAll(brandSpec);
        return brands;
    }*/

    @Override
    public  Page<Brand> getBrands(Map<String, String> params) {
        // Constructs a filter for searching Brands based on parameters
        BrandFilter brandFilter = new BrandFilter();

        if(params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);

        }
        if(params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        // Set the page limit, defaulting to a predefined value
        int pageLimit= PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        // Set the page number, defaulting to a predefined value
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        // Create a specification based on the filter and define the pagination
        BrandSpec brandSpec = new BrandSpec(brandFilter);
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        // Fetch the page of Brands matching the specification and pagination
        Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
        return page;
    }

/*

    @Override
    public List<Brand> getBrands() {
        return  brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrands(String name) {
//        return brandRepository.findByNameLike("%"+ name + "%");
        return brandRepository.findByNameContaining(name);
    }
*/

}
