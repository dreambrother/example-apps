import ch.qos.logback.classic.encoder.PatternLayoutEncoder

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%relative %t %level %-15logger{0} %m %n"
    }
}

root(INFO, ["CONSOLE"])
