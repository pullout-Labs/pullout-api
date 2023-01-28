dependencies {
    api(project(":pullout-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-quartz") // scheduler
    implementation("org.springframework.batch:spring-batch-core") // batch
}