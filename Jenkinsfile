pipeline {
    agent any

    tools {
        jdk   'jdk-21'        
        maven 'maven-3.9.6'   
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/joseprogramacion2/parcial-aseguramiento2.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B -ntp clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B -ntp test'
            }
            post {
                always {
                    
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B -ntp package'
            }
        }

        stage('Run') {
            steps {
                
                bat 'mvn -B -ntp exec:java -Dexec.mainClass=com.mycompany.calculadora.Main'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, allowEmptyArchive: true
            echo '✅ Pipeline completado con éxito.'
        }
        failure {
            echo '❌ Pipeline falló.'
        }
    }
}
