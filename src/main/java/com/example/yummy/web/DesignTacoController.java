package com.example.yummy.web;

import com.example.yummy.Ingredient;
import com.example.yummy.Order;
import com.example.yummy.Taco;
import com.example.yummy.data.IngredientsRepository;
import com.example.yummy.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.yummy.Ingredient.Type;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientsRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    @Autowired
    public DesignTacoController(IngredientsRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByTpe(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String proccessDesign(@Valid @ModelAttribute("design") Taco taco, Errors error, @ModelAttribute Order order) {
        if(error.hasErrors()){
            return "design";
        }
        Taco mask = tacoRepository.save(taco);
        order.addDesign(mask);
        log.info("Processing Taco design for " + taco.getName());
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByTpe(List<Ingredient> ingredients, Type type){
        return  ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    }
}
