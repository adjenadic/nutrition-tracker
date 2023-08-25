package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.data.datasource.MainDataSource
import raf.rs.nutritiontracker.model.entities.Area

class AreasRepositoryImpl(private val mainDataSource: MainDataSource) : AreasRepository {
    override fun getAllAreas(): Observable<List<Area>> {
        return mainDataSource
            .getAllAreas()
            .map {
                it.meals.map { areaResponse ->
                    Area(
                        strArea = areaResponse.strArea!!
                    )
                }
            }
    }
}