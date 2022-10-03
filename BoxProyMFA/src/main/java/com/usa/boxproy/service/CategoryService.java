package com.usa.boxproy.service;

import com.usa.boxproy.entities.Category;
import com.usa.boxproy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    public Optional<Category> getCategory(int idCategory){
        return categoryRepository.getCategory(idCategory);
    }
    public Category save(Category cat){
        if (cat.getId()==null){
            return categoryRepository.save(cat);
        }else{
            Optional<Category> e = categoryRepository.getCategory(cat.getId());
            if (e.isEmpty()){
                return categoryRepository.save(cat);
            }else{
                return cat;
            }
        }
    }
    public Category update(Category cat) {
        if (cat.getId() != null) {
            Optional<Category> e = categoryRepository.getCategory(cat.getId());
            if (!e.isEmpty()) {
                if (cat.getName() != null) {
                    e.get().setName(cat.getName());
                }
                if (cat.getDescription() != null) {
                    e.get().setDescription(cat.getDescription());
                }
                categoryRepository.save(e.get());
                return e.get();
            }else {
                return cat;
            }
        }else {
            return cat;
        }
    }

    public boolean delete(int idCategory){
        boolean flag=false;
        Optional<Category>cat= categoryRepository.getCategory(idCategory);
        if(cat.isPresent()){
            categoryRepository.delete(cat.get());
            flag=true;
        }
        return flag;
    }
}
