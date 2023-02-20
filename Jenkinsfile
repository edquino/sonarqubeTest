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
                        sh "${SCANNER_HOME}/bin/sonar-scanner -e -Dsonar.host.url=http://192.168.228.3:9000 -Dsonar.login=${sonarLogin} -Dsonar.projectName=SonarqubeTest -Dsonar.projectVersion=${env.BUILD_NUMBER} -Dsonar.projectKey=develop -Dsonar.sources=src/main/ -Dsonar.language=java -Dsonar.java.binaries=."
                    } 
                }
            }
        }

        stage('Quality Gate'){
            steps{
                waitForQualityGate abortPipeline: true
                /*timeout(time: 7, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                    waitForQualityGate abortPipeline: true
                }*/
            }
        }
    }
}