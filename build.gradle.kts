import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("idea")
  kotlin("jvm").version("1.3.30")
  application
  id("kotlinx-serialization").version("1.3.30")
}

group = "org.ramonbrand"
version = "0.0.1"

repositories {
  mavenCentral()
  maven("https://kotlin.bintray.com/kotlinx")
  jcenter()
}

dependencies {
  compile(kotlin("stdlib-jdk8"))
  compile("io.reactivex.rxjava2:rxkotlin:2.3.0")
//  implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")
  api("io.reactivex.rxjava2:rxjava:2.2.0")
  compile("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.0")
  compile("com.importre:crayon:0.1.0")
}

application {
  mainClassName = "AppKt"
}

tasks.create<Jar>("buildDependencies") {
  baseName = "george_dependencies"
  from(configurations.runtimeClasspath.get().map {
    if (it.isDirectory) it else zipTree(it)
  })
  doLast {
    delete("$rootDir/build/tmp/")
  }
}

tasks.withType<JavaExec> {
  doLast {
    delete("$rootDir/build/kotlin/")
  }
}

tasks.withType<Jar> {
  manifest {
    attributes["Main-Class"] = "AppKt"
  }
  doLast {
    delete("$rootDir/build/tmp/")
    delete("$rootDir/build/kotlin/")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}
