package es.jarroyo.revolut.app.di

import dagger.Component
import es.jarroyo.revolut.app.di.component.ApplicationComponent
import es.jarroyo.revolut.app.di.module.DomainModule
import es.jarroyo.revolut.app.di.module.RepositoryModule
import es.jarroyo.revolut.app.di.module.TestDataModule
import es.jarroyo.revolut.app.di.module.TestUtilsModule
import es.jarroyo.revolut.app.di.viewmodel.ViewModelFactoryModule
import es.jarroyo.revolut.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(TestApplicationModule::class,
        TestUtilsModule::class,
        DomainModule::class,
        TestDataModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class)
)
interface TestApplicationComponent: ApplicationComponent {
}