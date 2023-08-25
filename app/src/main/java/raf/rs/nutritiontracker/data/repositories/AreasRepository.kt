package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.entities.Area

interface AreasRepository {
    fun getAllAreas(): Observable<List<Area>>
}