pipeline {
    agent {
        docker {
            image 'docker:24.0.2-cli'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    options {
        skipDefaultCheckout(true)
    }

    environment {
        IMAGE_NAME = 'hello-app'
        CONTAINER_NAME = 'hello-app-container'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR with Maven') {
            steps {
                sh '''
                    apk add --no-cache openjdk17 maven
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage('Run Docker Container') {
            steps {
                sh """
                    docker rm -f $CONTAINER_NAME || true
                    docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME
                """
            }
        }
    }
}
