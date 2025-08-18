pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9' }
  stages {
    stage('Checkout'){ steps { checkout scm } }
    stage('Build & Test'){ steps { bat 'mvn -B -U clean test' } }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
    }
  }
}
