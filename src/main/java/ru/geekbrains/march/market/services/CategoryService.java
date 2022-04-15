package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Category;
import ru.geekbrains.march.market.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategory (){
       return  categoryRepository.findAll();
    }


    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Optional<Category> findIdByTitle(String categoryTitle) {
        return  categoryRepository.findIdByTitle(categoryTitle);
    }
}
