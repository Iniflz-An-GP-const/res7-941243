package gnrco.res7.mobile.data.repository

import gnrco.res7.mobile.data.database.dao.HighScoreDao
import gnrco.res7.mobile.data.entity.HighScore
import gnrco.res7.mobile.data.model.BestScores
import gnrco.res7.mobile.data.model.Difficulty
import gnrco.res7.mobile.data.model.GameResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class HighScoreRepository(
    private val highScoreDao: HighScoreDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun getBest(difficulty: Difficulty): BestScores {
        return withContext(coroutineDispatcher) {
            highScoreDao.getBest(difficulty)
        }
    }

    fun observeBest(difficulty: Difficulty): Flow<BestScores> = highScoreDao.observeBest(difficulty)

    suspend fun submit(difficulty: Difficulty, moves: Int, timeMs: Long): GameResult {
        return withContext(coroutineDispatcher) {
            val previous = highScoreDao.getBest(difficulty)

            highScoreDao.saveRun(
                HighScore(
                    difficulty = difficulty,
                    moves = moves,
                    timeMs = timeMs,
                    timestamp = LocalDateTime.now(),
                )
            )

            val previousMoves = previous.bestMoves
            val previousTimeMs = previous.bestTimeMs

            GameResult(
                moves = moves,
                timeMs = timeMs,
                isNewBestMoves = previousMoves == null || moves < previousMoves,
                isNewBestTime = previousTimeMs == null || timeMs < previousTimeMs,
                best = BestScores(
                    bestMoves = minOf(moves, previousMoves ?: moves),
                    bestTimeMs = minOf(timeMs, previousTimeMs ?: timeMs),
                ),
            )
        }
    }

    suspend fun getAll(): List<HighScore> {
        return withContext(coroutineDispatcher) {
            highScoreDao.getAll()
        }
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            highScoreDao.deleteAll()
        }
    }
}
