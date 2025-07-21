pipeline {
    agent {
        docker {
            image 'docker:24.0.2-dind'
            args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        MAVEN_HOME = '/usr/share/maven'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'
        PATH = "$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"
    }

    stages {

        stage('Clone Repo') {
            steps {
                // Assumes this Jenkinsfile is in SCM or repo is declared in the Jenkins job config
                git url: 'https://github.com/selvalives/hello-app.git', branch: 'main'
            }
        }

        stage('Install Java & Maven') {
            steps {
                sh '''
                apk add --no-cache openjdk17 maven
                java -version
                mvn -version
                '''
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t hello-app .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 hello-app'
            }
        }
    }
}
