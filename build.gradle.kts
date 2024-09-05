plugins {
    id ("application")
    id ("java")
    id ("com.github.johnrengelman.shadow") version "7.0.0"
} 

repositories {
    mavenCentral()
}

application {
    mainClass = "com.kireiiiiiiii.shooting_stars.AppMain"
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
}

tasks.jar {
    archiveFileName.set("ShootingStars.jar")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveFileName.set("$buildDir/libs/ShootingStars.jar")
    from("src/main/Resources")
    manifest {
        attributes["Main-Class"] = "com.kireiiiiiiii.shooting_stars.AppMain"
    }
}

tasks.shadowJar {
    mergeServiceFiles()
    archiveClassifier.set("")
}
