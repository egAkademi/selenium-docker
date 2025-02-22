pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                bat 'docker build -t=gkhnsit/selenium:latest .'
            }
        }

        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                bat 'docker push gkhnsit/selenium:latest'
                bat "docker tag gkhnsit/selenium:latest gkhnsit/selenium:${env.BUILD_NUMBER}"
                bat "docker push gkhnsit/selenium:${env.BUILD_NUMBER}"
            }
        }

    }

    post {
        always {
            bat 'docker logout'
        }
    }

}