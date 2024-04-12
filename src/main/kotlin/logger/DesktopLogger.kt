package logger

import org.lighthousegames.logging.Logger
import org.slf4j.LoggerFactory

object DesktopLogger {
    private val slf4jLogger: org.slf4j.Logger = LoggerFactory.getLogger(ch.qos.logback.classic.Logger::class.java)

    val logger: Logger
        get() = object : Logger {
            override fun debug(tag: String, msg: String) {
                slf4jLogger.debug(msg)
            }

            override fun error(tag: String, msg: String, t: Throwable?) {
                slf4jLogger.error(msg)
            }

            override fun info(tag: String, msg: String) {
                slf4jLogger.info(msg)
            }

            override fun isLoggingDebug(): Boolean {
                return slf4jLogger.isDebugEnabled
            }

            override fun isLoggingError(): Boolean {
                return slf4jLogger.isErrorEnabled
            }

            override fun isLoggingInfo(): Boolean {
                return slf4jLogger.isInfoEnabled
            }

            override fun isLoggingVerbose(): Boolean {
                return true
            }

            override fun isLoggingWarning(): Boolean {
                return slf4jLogger.isWarnEnabled
            }

            override fun verbose(tag: String, msg: String) {

            }

            override fun warn(tag: String, msg: String, t: Throwable?) {
                slf4jLogger.warn(msg)
            }
        }
}