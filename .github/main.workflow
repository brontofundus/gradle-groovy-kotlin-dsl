action "gradle build" {
  uses = "docker://gradle:5.4.0-jdk8"
  runs = ["sh", "-c", "gradle build"]
}
