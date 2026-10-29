package gnrco.res7.mobile.di

import androidx.room.Room
import gnrco.res7.mobile.data.database.GCEOEAppDatabase
import gnrco.res7.mobile.data.repository.HighScoreRepository
import gnrco.res7.mobile.data.model.Difficulty
import gnrco.res7.mobile.ui.viewmodel.GameViewModel
import gnrco.res7.mobile.ui.viewmodel.HighScoreViewModel
import gnrco.res7.mobile.ui.viewmodel.MenuViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "gceoe_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = GCEOEAppDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<GCEOEAppDatabase>().highScoreDao() }

    single {
        HighScoreRepository(
            highScoreDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}

val viewModule = module {
    viewModel { (difficulty: Difficulty) ->
        GameViewModel(difficulty = difficulty, highScoreRepository = get())
    }

    viewModel { HighScoreViewModel(highScoreRepository = get()) }

    viewModel { MenuViewModel(highScoreRepository = get()) }
}

val appModules: List<Module> = listOf(dispatcherModule, dataModule, viewModule)
