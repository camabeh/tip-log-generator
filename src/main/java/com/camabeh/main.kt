package com.camabeh

import com.thedeanda.lorem.LoremIpsum
import org.slf4j.LoggerFactory

object Main {
  val log = LoggerFactory.getLogger(this::class.java.name)
  
  fun log(): (String) -> Unit {
    val random = (Math.random() * 100).toInt()
    return when (random) {
      in 0..20 -> log::error
      in 21..40 -> log::warn
      in 41..80 -> log::info
      in 81..90 -> log::debug
      else -> log::trace
    }
  }

  fun throwException(allow: Boolean) {
    try {
      if (allow) {
        throw RuntimeException("I am runtime exception")
      }
    } catch (e: Exception) {
      log.error(e.message, e)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val loremGenerator = LoremIpsum.getInstance()
    val timeoutInSeconds = System.getenv("INTERVAL")?.toLong() ?: 1

    while (true) {
      val randomLevelLogger = log()
      randomLevelLogger(loremGenerator.getWords(4, 15))
      throwException(Math.random() < 0.25)
      Thread.sleep(1000 * timeoutInSeconds)
    }

  }
}