pipeline {
  agent any

  options {
    timestamps()
    skipDefaultCheckout(true)   // evita el checkout automático de Jenkins
  }

  tools {
    jdk   'jdk-21'
    maven 'maven-3.9.6'
  }

  environment {
    REPO_URL = 'https://github.com/joseprogramacion2/parcial-aseguramiento2.git'
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: env.REPO_URL
        // Muestra el árbol para verificar dónde está el pom
        bat 'dir /b'
        bat 'dir /s /b pom.xml'
      }
    }

    stage('Locate POM') {
      steps {
        script {
          // Candidatos comunes; agrega otros nombres si aplica
          def candidates = ['.', 'Calculadora', 'CalculadoraJenkins', 'app', 'java', 'src']
          def found = null
          for (c in candidates) {
            if (fileExists("${c}/pom.xml")) { found = c; break }
          }
          if (found == null) {
            // Búsqueda más amplia
            def out = bat(returnStdout: true, script: 'for /r %i in (pom.xml) do @echo %~dpi').trim()
            if (out) {
              // Toma la primera línea y normaliza separadores
              found = out.readLines()[0].replace('\\','/').replaceAll('/$', '')
            }
          }
          if (found == null || !fileExists("${found}/pom.xml")) {
            error "No se encontró pom.xml en el repo. Asegúrate de que el proyecto Maven existe o ajusta el path."
          }
          env.POM_DIR = found
          echo "Usando POM en: ${env.POM_DIR}/pom.xml"
        }
      }
    }

    stage('Build') {
      steps {
        dir(env.POM_DIR) {
          bat 'mvn -B -ntp clean compile'
        }
      }
    }

    stage('Test') {
      steps {
        dir(env.POM_DIR) {
          bat 'mvn -B -ntp test'
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Package') {
      steps {
        dir(env.POM_DIR) {
          bat 'mvn -B -ntp package'
        }
      }
    }

    stage('Run (opcional)') {
      when { expression { return fileExists("${env.POM_DIR}/pom.xml") } }
      steps {
        dir(env.POM_DIR) {
          // Ajusta la clase main si es distinta
          bat 'mvn -B -ntp exec:java -Dexec.mainClass=com.mycompany.calculadora.Main'
        }
      }
    }
  }

  post {
    success {
      archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, allowEmptyArchive: true
      echo '✅ Pipeline OK'
    }
    failure {
      echo '❌ Pipeline falló'
    }
  }
}



