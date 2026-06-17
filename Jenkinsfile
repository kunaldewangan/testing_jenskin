pipeline {
    // 1. Start on any available agent without requiring a container upfront
    agent any

    // tools {
    //     // 2. Load the Docker CLI tool immediately so the 'docker' command works(this name can be found in jenkins->tool->docker installation -> name)
    //     dockerTool 'docker-cli-from-jenkins'
    // }

    

    environment {

        // Forces the host Docker daemon to bypass strict version blocking
        DOCKER_API_VERSION = "1.44"
        
        REGISTRY = "local-registry"
        IMAGE_NAME = "dummy-spring-app-testing-jenskin"
        IMAGE_TAG = "latest"
        CONTAINER_NAME = "dummy-spring-testing-jenskin"
        PORT = "8080"
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Build Application') {
            // 3. Move the Java 25 Docker container restriction inside this specific stage
            agent {
                docker {
                    image 'eclipse-temurin:25-jdk' 
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                sh 'chmod +x mvnw'
                // This now executes safely inside the Java 25 container
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // This runs on the host engine using your tool
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Deploy to Local Docker') {
            steps {
                script {
                    try {
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    } catch (Exception e) {
                        echo "No existing container found to stop."
                    }
                    sh "docker run -d -p ${PORT}:${PORT} --name ${CONTAINER_NAME} ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
    }
}
