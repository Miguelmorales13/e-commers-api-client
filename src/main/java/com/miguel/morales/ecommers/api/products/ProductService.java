package com.miguel.morales.ecommers.api.products;

import com.miguel.morales.ecommers.api.crud.HibernateService;
import com.miguel.morales.ecommers.api.products.dto.CreateProductDto;
import com.miguel.morales.ecommers.api.products.dto.UpdateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements HibernateService<ProductModel, CreateProductDto, UpdateProductDto> {
    @Autowired
    private ProductRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductModel> getAll() {
        return (List<ProductModel>) userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProductModel> getAllByAny(Long categoryId) {
        return userRepository.findAllByCategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductModel> getOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public ProductModel create(CreateProductDto item) {
        return userRepository.save(new ProductModel());
    }

    @Override
    @Transactional
    public ProductModel update(UpdateProductDto item, Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.save(new ProductModel());
        }
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            userRepository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
