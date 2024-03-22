plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

group = "dev.kobalt"
version = "0000.00.00.00.00.00.000"

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
}

fun general(module: String, version: String) = "$module:$version"
fun kotlinx(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$module:$version"
fun libgdx(version: String) = "com.badlogicgames.gdx:gdx:$version"
fun libgdx(module: String, version: String) = "com.badlogicgames.gdx:gdx-$module:$version"
fun libktx(module: String, version: String) = "io.github.libktx:ktx-$module:$version"

fun DependencyHandler.libgdxEngine() {
    implementation(libgdx("1.10.0"))
    implementation(libgdx("backend-lwjgl3", "1.10.0"))
    implementation(libgdx("platform", "1.10.0:natives-desktop"))
}

fun DependencyHandler.libktxExtensions() {
    implementation(libktx("app", "1.10.0-b4"))
    implementation(libktx("assets", "1.10.0-b4"))
    implementation(libktx("assets-async", "1.10.0-b4"))
    implementation(libktx("async", "1.10.0-b4"))
    implementation(libktx("collections", "1.10.0-b4"))
    implementation(libktx("graphics", "1.10.0-b4"))
    implementation(libktx("math", "1.10.0-b4"))
}

fun DependencyHandler.standardLibrary() {
    implementation(kotlin("stdlib", "1.6.10"))
}

fun DependencyHandler.coroutines() {
    implementation(kotlinx("coroutines-core","1.6.0"))
}

dependencies {
    standardLibrary()
    coroutines()
    libgdxEngine()
    libktxExtensions()
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("openalchemy.jar")
        mergeServiceFiles()
        // minimize()
        manifest {
            attributes("Main-Class" to "dev.kobalt.openalchemy.jvm.MainKt")
        }
    }
}