plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "IDM-BACKEND"
include("common")
include("api")
include("domain")
