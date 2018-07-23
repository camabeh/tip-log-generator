package com.camabeh

import com.thedeanda.lorem.LoremIpsum
import org.slf4j.LoggerFactory

object Main {
  val log = LoggerFactory.getLogger(this::class.java.name)

  fun renderer(thread: String, clazz: String, msg: String): String {
    return "[$thread] ($clazz) $msg";
  }

  fun log(): (String) -> Unit {
    val random = (Math.random() * 23).toInt()
    return when (random) {
      in 0..2 -> log::error
      in 3..6 -> log::warn
      in 7..10 -> log::info
      in 11..20 -> log::debug
      else -> log::trace
    }
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val lorem = LoremIpsum.getInstance()
    val timoutInSeconds = System.getenv("INTERVAL")?.toLong() ?: 1

    while (true) {
      val randomLevelLogger = log()
      randomLevelLogger(renderer(lorem.getWords(1), lorem.getWords(1), lorem.getWords(4, 15)))
      Thread.sleep(1000 * timoutInSeconds)
    }

  }
}