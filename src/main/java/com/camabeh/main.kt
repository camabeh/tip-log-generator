package com.camabeh

import com.thedeanda.lorem.LoremIpsum
import org.slf4j.LoggerFactory

object Main {
  val log = LoggerFactory.getLogger(this::class.java.name)

  fun renderer(thread: String, msg: String): String {
    return "($thread) $msg";
  }

  fun log(msg: String) {
    val random = (Math.random() * 23).toInt()
    when (random) {
      in 0..2 -> log.error(msg)
      in 3..6 -> log.warn(msg)
      in 7..10 -> log.info(msg)
      in 11..20 -> log.debug(msg)
      else -> log.trace(msg)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) {
    val lorem = LoremIpsum.getInstance()
    val timoutInSeconds = System.getenv("INTERVAL")?.toLong() ?: 1

    while (true) {
      log(renderer(lorem.getWords(1), lorem.getWords(4, 15)))
      Thread.sleep(1000 * timoutInSeconds)
    }

  }
}