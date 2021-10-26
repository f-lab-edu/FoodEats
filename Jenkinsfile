pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }

    environment {
        SOURCECODE_JENKINS_CREDENTIAL_ID = 'jenkins-github-wh'
        SOURCE_CODE_URL = 'https://github.com/f-lab-edu/FoodEats.git'
        RELEASE_BRANCH = 'main'
    }

    stages {
        stage('Init') {
            steps {
                echo 'clear'
                sh 'docker stop $(docker ps -aq)'
                sh 'docker rm $(docker ps -aq)'
                sh 'docker rmi $(docker images -q)'
                deleteDir()
            }
        }

        stage('clone') {
            steps {
                git url: "$SOURCE_CODE_URL",
                    branch: "$RELEASE_BRANCH",
                    credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
                sh "ls -al"
            }
        }

        stage('dockerizing') {
            steps {
                sh "pwd"
                sh "chmod +x ./gradlew"
                sh "./gradlew build --stacktrace"

                sh "docker build -t foodeats ."
            }
        }

        stage('deploy') {
            steps {
                sh "docker-compose up --build -d"
            }
        }
    }
}