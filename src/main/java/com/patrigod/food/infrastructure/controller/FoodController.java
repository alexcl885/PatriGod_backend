package com.patrigod.food.infrastructure.controller;

import java.util.List;

import com.patrigod.food.application.CreateFoodUseCase;
import com.patrigod.food.application.DeleteFoodByIdUseCase;
import com.patrigod.food.application.GetAllFoodUseCase;
import com.patrigod.food.application.GetFoodByIdUseCase;
import com.patrigod.food.domain.entity.Food;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.food.infrastructure.controller.dto.input.FoodInputDto;
import com.patrigod.food.infrastructure.controller.dto.output.FoodOutputDto;
import com.patrigod.food.application.mapper.FoodMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodMapper foodMapper;

    private final CreateFoodUseCase createFoodUseCase;
    private final GetFoodByIdUseCase getFoodByIdUseCase;
    private final GetAllFoodUseCase getAllFoodUseCase;
    private final DeleteFoodByIdUseCase deleteFoodByIdUseCase;

    /**
     * Retrieves the list of all food items.
     * @return a list of food items in output DTO format
     */
    @GetMapping
    public ResponseEntity<List<FoodOutputDto>> findAllFood() {
        List<FoodOutputDto> foods =  getAllFoodUseCase.getAllFood().stream()
                .map(foodMapper::toFoodOutputDto)
                .toList();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    /**
     * Saves a new FoodInputDto to the database.
     * @param foodInputDto input DTO containing the food data
     * @return the saved food in output DTO format
     */
    @PostMapping
    public FoodOutputDto createFood(@RequestBody FoodInputDto foodInputDto) {
        Food food = foodMapper.foodInputDtoToFood(foodInputDto);
        Food createdFood = createFoodUseCase.createFood(food, foodInputDto.getCityId());
        return foodMapper.toFoodOutputDto(createdFood);
    }

    /**
     * Deletes a food item by its identifier.
     * @param id the identifier of the food item to delete
     * @return ResponseEntity with the deleted food item in output DTO format
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteFood(@PathVariable Long id){
        deleteFoodByIdUseCase.deleteFoodById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT);

    }

}
