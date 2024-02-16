import org.springframework.boot.gradle.tasks.bundling.BootJar

val querydslVersion: String by System.getProperties()

plugins {
    kotlin("kapt")
    kotlin("plugin.jpa") // intellij idea에서 사용할 수 있도록 추가
    idea
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity") // @Entity가 붙은 클래스에 한해서만 no arg 플러그인을 적용
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

val kotestVersion: String by System.getProperties()

dependencies {
    api(project(":common"))
    val kapt by configurations // // spring-boot-starter
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-security") // javax.servlet 사용을 위한 라이브러리 - filter에서 사용 9버전까지 사용 가능하며 10버전 이후론 삭제된듯하다.
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.65") // jwt
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("io.jsonwebtoken:jjwt-gson:0.11.5") // implementation을 api로 변경하면 오류가 발생한다??
    implementation("io.jsonwebtoken:jjwt-api:0.11.5") // implementation을 api로 변경하면 오류가 발생한다??
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    api("com.querydsl:querydsl-kotlin-codegen:$querydslVersion") // kotlin code generation support
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa") // 이게 없으면 build해도 Q class가 생성되지 않는다.
// kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:5.3.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2") // kotlin-faker
    testImplementation("io.github.serpro69:kotlin-faker:1.11.0")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}
