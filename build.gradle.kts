plugins {
    id("java")
    id("application")
}

group = "ru.danilshkuratetskiy"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "Main"
}

tasks.register("fatJar", Jar::class) {
    group = "build"
    description = "Собирает исполняемый jar с зависимостями"

    manifest {
        attributes["Main-Class"] = application.mainClass
    }

    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.exists() }.map {
            if (it.isDirectory) it else zipTree(it)
        }
    })
}