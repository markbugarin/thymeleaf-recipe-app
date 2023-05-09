package com.champstart.recipeapp.procedure.model;

import com.champstart.recipeapp.recipe.model.RecipeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class ProcedureModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String recipeProcedure;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeModel recipeModel;
}
