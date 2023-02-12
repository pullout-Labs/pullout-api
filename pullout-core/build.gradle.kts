import java.util.regex.Pattern.compile

plugins {
    jacoco
}

jacoco {
    toolVersion = "0.8.5"

}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api ("org.springframework.boot:spring-boot-starter-security")
    runtimeOnly("com.h2database:h2")
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("io.jsonwebtoken:jjwt:0.9.1")
    implementation ("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql:8.2.1")


}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("./src/test/kotlin/integration", "./src/test/kotlin/unit")) // test Dirs
        }
    }
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }

    finalizedBy("jacocoTestReport")
}

/**
 * Jacoco - For Test Code Coverage
 * Test Code 실행 결과 확인
 */
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            /** test target
             * BUNDLE : 패키지 번들(프로젝트 모든 파일을 합친 것. default)
             * CLASS : 클래스
             * GROUP : 논리적 번들 그룹
             * METHOD : 메서드
             * PACKAGE : 패키지
             * SOURCEFILE : 소스 파일
             */
            element = "CLASS" // 커버리지를 체크할 기준(단위)
            /**
             * BRANCH : 조건문 등의 분기 수
             * CLASS : 클래스 수, 내부 메서드가 한 번이라도 실행된다면 실행된 것으로 간주한다.
             * COMPLEXITY : 복잡도
             * INSTRUCTION : Java 바이트코드 명령 수 (default)
             * METHOD : 메서드 수, 메서드가 한 번이라도 실행된다면 실행된 것으로 간주한다.
             * LINE : 빈 줄을 제외한 실제 코드의 라인 수, 라인이 한 번이라도 실행되면 실행된 것으로 간주한다.
             */
            limit {
                counter = "BRANCH" //  커버리지 측정의 최소 단위
                value = "COVEREDRATIO" // value는 limit 메서드를 통해 지정할 수 있으며 측정한 커버리지를 어떠한 방식으로 보여줄 것인지 설정
                minimum = "0.90".toBigDecimal()
            }

            // 커버리지를 측정할 때 제외할 클래스를 지정
            excludes = listOf(
                "./src/main"
            )
        }
    }
}