/*
 * © Copyright 2022 Micro Focus or one of its affiliates.
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microfocus.lrc.core.entity

import kotlin.math.pow

class TestRunResultsResponse(
    val status: String,
    val duration: String,
    val percentileValue: Int,
    val totalVusers: Int,
    val averageThroughput: String,
    val totalThroughput: String,
    val averageHits: String,
    val totalHits: Int,
    val totalTransactionsPassed: Int,
    val totalTransactionsFailed: Int,
    val scriptErrors: Int,
) {
    constructor() : this("", "", 0, 0, "", "", "", 0, 0, 0, 0)

    companion object {
        @JvmStatic
        fun rmThroughputUnit(withUnit: String): Double {
            val split = withUnit.split(" ")
            if (split.size != 2) {
                return -1.0
            }
            val unit = split[1].removeSuffix("/s")
            val num = split[0].toDouble()

            var result = num
            val allUnits = arrayOf("bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB")
            val i = allUnits.indexOf(unit)
            if (i != -1) {
                result = num * (1024.0.pow(i))
            }

            return result
        }
    }

    fun getDurationInSec(): Int {
        val duration = duration.split(":")
        val hours = duration[0].toInt()
        val minutes = duration[1].toInt()
        val seconds = duration[2].toInt()
        return hours * 3600 + minutes * 60 + seconds
    }

    fun getErrorsPerSec(): Double {
        return scriptErrors.toDouble() / getDurationInSec().toDouble()
    }

    fun getAvgHitsNum(): Double {
        return averageHits.removeSuffix(" hits/s").toDouble()
    }

    fun getAvgThroughputWithOutUnit(): Double {
        return rmThroughputUnit(averageThroughput)
    }

    fun getTotalThroughputWithOutUnit(): Double {
        return rmThroughputUnit(totalThroughput)
    }
}

class TestRunTransactionsResponse(
    val name: String,
    val loadTestScriptId: Int,
    val scriptName: String,
    val minTRT: Double,
    val maxTRT: Double,
    val avgTRT: Double,
    val percentileTRT: Double,
    val breakers: Double,
    val slaStatus: String,
    val slaThreshold: Double,
    val stdDeviation: Double,
    val passed: Int,
    val failed: Int,
    val slaTrend: Double,
)

class TestRunTrtSummaryResponse(
    val name: String,
    val loadTestScriptId: Int,
    val scriptName: String,
    val maxTRT: Double,
    val avgTRT: Double,
    val minTRT: Double,
    val passed: Int,
    val failed: Int,
    val successRate: Double,
    val avgTPS: Double,
    val stdDeviation: Double,
)

class LoadTestTransactionsResponse(
    val id: Int,
    val enabled: Boolean,
    val scriptId: Int,
    val scriptName: String,
    val testScriptId: Int,
    val transactionName: String,
    val slaPercentileThreshold: Double,
    val stopOnBreak: Boolean,
    val failedTrxRatio: Double,
    val failedTrxEnabled: Boolean,
)
