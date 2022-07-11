package com.example.food_donate;

public class FoodDetail {
    String foodName;
    String foodType;
    String restaurantName;
    String people;

    public FoodDetail()
    {

    }
    public FoodDetail(String foodName, String foodType, String restaurantName,String people) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.restaurantName = restaurantName;
        this.people = people;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPeople() {
        return people;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
