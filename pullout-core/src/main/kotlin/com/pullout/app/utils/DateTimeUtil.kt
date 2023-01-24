package com.pullout.app.utils

import java.time.*


/**
 * Korea LocalDateTime handler
 */
class DateTimeUtil {

    companion object {
        @JvmStatic
        private val zoneId: ZoneId = ZoneId.of("Asia/Seoul")

        /**
         * get kst DateTime
         */
        @JvmStatic
        fun kstDateTimeNow(): LocalDateTime = utcToKst(LocalDateTime.now()).toLocalDateTime()

        /**
         * get kst Date
         */
        @JvmStatic
        fun kstDateNow(): LocalDate = utcToKst(LocalDateTime.now()).toLocalDate()

        /**
         * get Kst Time
         */
        @JvmStatic
        fun kstTimeNow(): LocalTime = utcToKst(LocalDateTime.now()).toLocalTime()


        /**
         * KST ->  UTC
         *
         * @param kstDateTime
         * @return
         */
        @JvmStatic
        private fun kstToUtc(kstDateTime: LocalDateTime): ZonedDateTime {
            val zonedDateTime = ZonedDateTime.of(kstDateTime, zoneId)
            return zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))
        }

        @JvmStatic
        fun kstToUtc(localDate: LocalDate): ZonedDateTime {
            val localDateTime: LocalDateTime = localDate.atTime(0, 0, 0)
            return kstToUtc(localDateTime)
        }

        /**
         * UTC ->  KST
         *
         * @param utcDateTime
         * @return ZonedDateTime (Zone : Asia/Seoul )
         */
        @JvmStatic
        private fun utcToKst(utcDateTime: LocalDateTime): ZonedDateTime {
            val zonedDateTime = ZonedDateTime.of(utcDateTime, ZoneId.of("UTC"))
            return zonedDateTime.withZoneSameInstant(zoneId)
        }


        /**
         * UTC OffsetDateTime to KST LocalDate
         *
         * @param offsetDateTime
         * @return
         */
        @JvmStatic
        fun offsetDateTimeToClientDate(offsetDateTime: OffsetDateTime): LocalDate {
            return offsetDateTimeToClientDateTime(offsetDateTime).toLocalDate()
        }

        /**
         * KST LocalDateTime to UTC OffsetDateTime
         *
         * @param localDateTime
         * @return
         */
        @JvmStatic
        fun clientDateTimeToOffsetDateTime(localDateTime: LocalDateTime): OffsetDateTime {
            return kstToUtc(localDateTime).toOffsetDateTime()
        }

        /**
         * UTC OffsetDateTime to KST LocalDateTime
         *
         * @param offsetDateTime
         * @return
         */
        @JvmStatic
        private fun offsetDateTimeToClientDateTime(offsetDateTime: OffsetDateTime): LocalDateTime {
            return utcToKst(offsetDateTime.toLocalDateTime()).toLocalDateTime()
        }

    }

}