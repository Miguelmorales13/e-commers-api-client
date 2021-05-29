package com.miguel.morales.ecommers.api.categories;

import com.miguel.morales.ecommers.api.categories.dto.CreateCategoriesProductDto;
import com.miguel.morales.ecommers.api.categories.dto.UpdateCategoriesProductDto;
import com.miguel.morales.ecommers.api.crud.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesProductService implements HibernateService<CategoriesProductModel, CreateCategoriesProductDto, UpdateCategoriesProductDto> {
    @Autowired
    private CategoriesProductRepository categoriesProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriesProductModel> getAll() {
        return categoriesProductRepository.findAllByNivel(1);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriesProductModel> getOne(Long id) {
        return categoriesProductRepository.findById(id);
    }

    @Override
    @Transactional
    public CategoriesProductModel create(CreateCategoriesProductDto item) {
        return categoriesProductRepository.save(new CategoriesProductModel());
    }

    @Override
    @Transactional
    public CategoriesProductModel update(UpdateCategoriesProductDto item, Long id) {
        if (categoriesProductRepository.findById(id).isPresent()) {
            return categoriesProductRepository.save(new CategoriesProductModel());
        }
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            categoriesProductRepository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
