package com.miguel.morales.ecommers.api.imagesProducts;

import com.miguel.morales.ecommers.api.crud.HibernateService;
import com.miguel.morales.ecommers.api.imagesProducts.dto.CreateImagesProductDto;
import com.miguel.morales.ecommers.api.imagesProducts.dto.UpdateImagesProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesProductService implements HibernateService<ImagesProductModel, CreateImagesProductDto, UpdateImagesProductDto> {
    @Autowired
    private ImagesProductRepository imagesProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ImagesProductModel> getAll() {
        return (List<ImagesProductModel>) imagesProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImagesProductModel> getOne(Long id) {
        return imagesProductRepository.findById(id);
    }

    @Override
    @Transactional
    public ImagesProductModel create(CreateImagesProductDto item) {
        return imagesProductRepository.save(new ImagesProductModel());
    }

    @Override
    @Transactional
    public ImagesProductModel update(UpdateImagesProductDto item, Long id) {
        if (imagesProductRepository.findById(id).isPresent()) {
            return imagesProductRepository.save(new ImagesProductModel());
        }
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            imagesProductRepository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
