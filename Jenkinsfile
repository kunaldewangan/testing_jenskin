pipeline {
    // 1. Start on any general agent to allow the Tool Installer to run first
    agent any

    tools {
        // 2. Load and install your custom configured modern Docker tool version (e.g., 27.3.1) defined in jenkins tool setting.
        //This can be find in jenkins-> tools-> docker installation->name;
        dockerTool 'docker-cli-from-jenkins'
    }

    environment {
        // Keeps client and engine synchronized
        DOCKER_API_VERSION = "1.44"
        
        REGISTRY = "local-registry"
        IMAGE_NAME = "dummy-spring-app-testing-jenskin"
        IMAGE_TAG = "latest"
        CONTAINER_NAME = "dummy-spring-testing-jenskin"
        PORT = "8080"
    }

    stages {
        stage('Pipeline Execution') {
            // 3. Bind the Java 25 workspace wrapper over ALL subsequent build steps
            agent {
                docker {
                    image 'eclipse-temurin:25-jdk' 
                    args '-v /var/run/docker.sock:/var/run/docker.sock -e DOCKER_API_VERSION=1.44'
                }
            }
            stages {
                stage('Clone Repository') {
                    steps {
                        checkout scm
                    } // end steps: Clone Repository
                } // end stage: Clone Repository

                stage('Build Application') {
                    steps {
                        sh 'chmod +x mvnw'
                        // Compiles using Java 25 and creates the 'target/' folder
                        sh './mvnw clean package -DskipTests'
                    } // end steps: Build Application
                } // end stage: Build Application

                stage('Build Docker Image') {
                    steps {
                        // Stays inside the same environment: 'target/' folder is found!
                        sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                    } // end steps: Build Docker Image
                } // end stage: Build Docker Image

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
                        } // end script block
                    } // end steps: Deploy to Local Docker
                } // end stage: Deploy to Local Docker
            } // end nested stages block
        } // end stage: Pipeline Execution
    } // end global stages block
} // end pipeline
