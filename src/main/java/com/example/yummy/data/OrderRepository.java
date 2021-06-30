package com.example.yummy.data;

import com.example.yummy.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Order,Long> {
}
