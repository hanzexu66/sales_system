package com.example.could.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class T_cart {
    private Integer item_id;
    private Integer user_id;
    private Integer cart_id;
}
