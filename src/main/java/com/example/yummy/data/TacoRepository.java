package com.example.yummy.data;

import com.example.yummy.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository <Taco,Long> {
}
