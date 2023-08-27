package raf.rs.nutritiontracker.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import raf.rs.nutritiontracker.data.repositories.AreasRepository
import raf.rs.nutritiontracker.data.repositories.AreasRepositoryImpl
import raf.rs.nutritiontracker.data.repositories.CategoriesRepository
import raf.rs.nutritiontracker.data.repositories.CategoriesRepositoryImpl
import raf.rs.nutritiontracker.data.repositories.IngredientsRepository
import raf.rs.nutritiontracker.data.repositories.IngredientsRepositoryImpl
import raf.rs.nutritiontracker.data.repositories.MealsDetailsRepository
import raf.rs.nutritiontracker.data.repositories.MealsDetailsRepositoryImpl
import raf.rs.nutritiontracker.data.repositories.MealsRepository
import raf.rs.nutritiontracker.data.repositories.MealsRepositoryImpl
import raf.rs.nutritiontracker.data.source.MainDataSource
import raf.rs.nutritiontracker.database.MealDB
import raf.rs.nutritiontracker.database.MealDBRepository
import raf.rs.nutritiontracker.database.MealDBRepositoryImpl
import raf.rs.nutritiontracker.database.MealDBViewModel
import raf.rs.nutritiontracker.ui.viewmodels.CategoriesViewModel
import raf.rs.nutritiontracker.ui.viewmodels.FilterViewModel
import raf.rs.nutritiontracker.ui.viewmodels.MealDetailsViewModel
import raf.rs.nutritiontracker.ui.viewmodels.MealsViewModel

val postsModule = module {
    viewModel {
        FilterViewModel(
            categoriesRepository = get(),
            areasRepository = get(),
            ingredientsRepository = get()
        )
    }

    single<IngredientsRepository> { IngredientsRepositoryImpl(mainDataSource = get()) }

    single<AreasRepository> { AreasRepositoryImpl(mainDataSource = get()) }


    viewModel { CategoriesViewModel(categoriesRepository = get()) }

    single<CategoriesRepository> { CategoriesRepositoryImpl(mainDataSource = get()) }


    viewModel { MealsViewModel(mealsRepository = get()) }

    single<MealsRepository> { MealsRepositoryImpl(mainDataSource = get()) }


    viewModel { MealDetailsViewModel(mealsDetailsRepository = get()) }

    single<MealsDetailsRepository> { MealsDetailsRepositoryImpl(mainDataSource = get()) }

    single<MainDataSource> { create(get()) }


    viewModel { MealDBViewModel(mealDBRepository = get()) }

    single<MealDBRepository> { MealDBRepositoryImpl(get()) }

    single { get<MealDB>().getMealDao() }
}