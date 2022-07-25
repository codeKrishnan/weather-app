package com.example.weatherapp.feature.base.di

import com.example.weatherapp.di.FeatureScope
import com.example.weatherapp.feature.base.BaseNavigationActivity
import dagger.Subcomponent

@FeatureScope
@Subcomponent(
    modules = [BaseNavigationModule::class]
)
interface BaseNavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BaseNavigationComponent
    }

    fun inject(baseNavigationActivity: BaseNavigationActivity)
}