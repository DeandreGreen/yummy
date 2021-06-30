package com.example.yummy.data;

import com.example.yummy.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository <Ingredient,String> {
}
