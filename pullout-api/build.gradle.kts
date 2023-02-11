
dependencies {
    api(project(":pullout-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("./src/test/kotlin/integration", "./src/test/kotlin/unit")) // test Dirs
        }
    }
}