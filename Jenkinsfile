pipeline {
    agent {
        docker {
            // This container runs Java 25 to safely compile your code
            image 'eclipse-temurin:25-jdk' 
            // Gives your Jenkins container access to the host machine's Docker engine
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    tools {
        // Setting Docker cli to execute docker command. Automatically installs and enables the Docker CLI executable tool inside this pipeline
        // This must match the exact Name you saved in your Jenkins Global Tool ->docker installation -> name settings
        dockerTool 'docker-cli-from-jenkins'
    }

    environment {
        REGISTRY = "local-registry"
        IMAGE_NAME = "dummy-spring-app-testing-jenskin"
        IMAGE_TAG = "latest"
        CONTAINER_NAME = "dummy-spring-testing-jenskin"
        PORT = "8080"
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Jenkins automatically clones the specific branch configured in the GUI
                checkout scm
            }
        }

        stage('Build Application') {
            steps {
                // Grant execute permission to the maven wrapper script
                sh 'chmod +x mvnw'
            
                // Compiles code and skips tests for speed using Java 25
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Builds the local docker image using the activated Docker tool
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Deploy to Local Docker') {
            steps {
                script {
                    // Stop and remove old container if it exists to avoid port conflicts
                    try {
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    } catch (Exception e) {
                        echo "No existing container found to stop."
                    }
                    // Run the new container
                    sh "docker run -d -p ${PORT}:${PORT} --name ${CONTAINER_NAME} ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
    }
}
