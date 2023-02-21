/*def qualityGateValidation(gg) {
    if(gg.status != 'OK'){
        return true
    }
    return false
}*/
pipeline {
    agent any
    tools{
        maven 'mavenjenkins'
    }
    stages {
         stage('Prepare scripts'){
            steps{
                sh 'chmod a+x jenkins/scripts/*.sh'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
            /*post{
                junit 'target/surefire-reports/*.xml'
            }*/
        }
        /*
        stage('Deploy') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
        */
        stage('Sonar Scanner') {
            environment {
                SCANNER_HOME = tool 'sonarScanner'
            }
            steps{
                withSonarQubeEnv('sonarqube'){
                    withCredentials([string(credentialsId: 'TokenSonarqube', variable: 'sonarLogin')]) {
                        sh "${SCANNER_HOME}/bin/sonar-scanner -Dsonar.host.url=http://192.168.56.101:9000 -Dsonar.projectName=SonarqubeTest -Dsonar.projectVersion=${env.BUILD_NUMBER} -Dsonar.projectKey=develop -Dsonar.sources=src/main -Dsonar.tests=target/jacoco.exec -Dsonar.java.binaries=. -Dsonar.language=java -Dsonar.java.source=11"
                    }
                }
            }
        }
        /*
        stage('Quality Gate'){
            steps{
                timeout(time: 3, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }*/
        stage("Quality Gate"){
            steps{
                timeout(time: 3, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
                    def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                }
            }   
        }

    }
}
