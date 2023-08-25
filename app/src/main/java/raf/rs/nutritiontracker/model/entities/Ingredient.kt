package raf.rs.nutritiontracker.model.entities

class Ingredient {
    var idIngredient = 0
    var strIngredient: String? = null
    var strDescription: String? = null
    var strType: String? = null

    constructor(
        idIngredient: Int,
        strIngredient: String?,
        strDescription: String?,
        strType: String?
    ) {
        this.idIngredient = idIngredient
        this.strIngredient = strIngredient
        this.strDescription = strDescription
        this.strType = strType
    }
}