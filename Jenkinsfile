pipeline {
    agent any

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
        
			// Now run the build command
			// Compiles code and skips tests for speed
			sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Builds the local docker image
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
