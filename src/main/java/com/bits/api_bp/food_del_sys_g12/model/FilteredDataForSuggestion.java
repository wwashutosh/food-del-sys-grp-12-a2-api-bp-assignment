package com.bits.api_bp.food_del_sys_g12.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilteredDataForSuggestion {
    private String id;
    private String name;
    private String type; // Type can be "Restaurant" or "Menu Item"


}