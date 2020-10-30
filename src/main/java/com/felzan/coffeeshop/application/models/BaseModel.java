package com.felzan.coffeeshop.application.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * <p>BaseModel provides:</p>
 * <ul>
 *     <li>Long id</li>
 *     <li>LocalDateTime created_at</li>
 *     <li>LocalDateTime updted_at</li>
 * </ul>
 */
@Data
@SuperBuilder
public abstract class BaseModel {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
