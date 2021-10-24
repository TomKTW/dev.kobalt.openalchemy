plugins {
    java
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "dev.kobalt"
version = "0000.00.00.00.00.00.000"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

@Suppress("unused")
fun DependencyHandler.general(module: String, version: String) = "$module:$version"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    api("com.badlogicgames.gdx:gdx:1.10.0")
    api(group = "io.github.libktx", name = "ktx-app", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-assets", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-assets-async", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-async", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-collections", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-graphics", version = "1.10.0-b4")
    api(group = "io.github.libktx", name = "ktx-math", version = "1.10.0-b4")
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.10.0")
    implementation("com.badlogicgames.gdx:gdx-platform:1.10.0:natives-desktop")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("game.jar")
        mergeServiceFiles()
        manifest {
            attributes("Main-Class" to "dev.kobalt.game.alchemy.MainKt")
        }
        //        minimize ()
        /*
        exclude(
            "LICENSE.txt",
            "NOTICE",
            "manifest.proto",
            "README.md",
            "versions-offline/**/*"
        )
         */
    }
}