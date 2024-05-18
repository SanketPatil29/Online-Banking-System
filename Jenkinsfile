pipeline {
    environment {
        DOCKERHUB_CREDENTIALS = credentials('DockerHubCred')
        MYSQL_CREDENTIALS = credentials('MySqlCred')
        DOCKERHUB_USER = 'kb1110'
    }
    agent any
    stages {
        stage('Clone repository') {
            steps {
                git branch: 'main', url: 'https://github.com/kb87-98/Online-Banking-System'
            }
        }
        stage('Maven Build RegistryService') {
            steps {
                echo 'Building RegistryService'
                sh 'cd ServiceRegistry && mvn clean install'
                sh 'mv -f ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar JarFiles/'
            }
        }
        stage('Maven Build APIGateway') {
            steps {
                echo 'Building APIGateway'
                sh 'cd ApiGateway && mvn clean install'
                sh 'mv -f ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar JarFiles/'
            }
        }
        stage('Maven Build Services') {
            parallel {
                stage('User Service') {
                    steps {
                        echo 'Building User Service'
                        sh "cd UserService && mvn clean install -DSPRING_DATASOURCE_USERNAME=${MYSQL_CREDENTIALS_USR} -DSPRING_DATASOURCE_PASSWORD=${MYSQL_CREDENTIALS_PSW}"
                        sh 'mv -f UserService/target/UserService-0.0.1-SNAPSHOT.jar JarFiles/'
                    }
                }
                stage('Account Service') {
                    steps {
                        echo 'Building Account Service'
                        sh "cd AccountService && mvn clean install -DSPRING_DATASOURCE_USERNAME=${MYSQL_CREDENTIALS_USR} -DSPRING_DATASOURCE_PASSWORD=${MYSQL_CREDENTIALS_PSW}"
                        sh 'mv -f AccountService/target/AccountService-0.0.1-SNAPSHOT.jar JarFiles/'
                    }
                }
                stage('Notification Service') {
                    steps {
                        echo 'Building Notification Service'
                        sh "cd NotificationService && mvn clean install -DSPRING_DATASOURCE_USERNAME=${MYSQL_CREDENTIALS_USR} -DSPRING_DATASOURCE_PASSWORD=${MYSQL_CREDENTIALS_PSW}"
                        sh 'mv -f NotificationService/target/NotificationService-0.0.1-SNAPSHOT.jar JarFiles/'
                    }
                }
            }
        }
        /*
        stage('Build Docker Images') {
            steps {
                echo 'Building Docker Images'
                sh "docker build -t ${DOCKERHUB_USER}/eurekaregistry -f DockerFiles/ServiceRegistryDockerfile ."
                sh "docker build -t ${DOCKERHUB_USER}/apigateway -f DockerFiles/APIGatewayServiceDockerfile ."
                sh "docker build -t ${DOCKERHUB_USER}/userservice -f DockerFiles/UserServiceDockerfile ."
                sh "docker build -t ${DOCKERHUB_USER}/accountservice -f DockerFiles/AccountServiceDockerfile ."
                sh "docker build -t ${DOCKERHUB_USER}/notificationservice -f DockerFiles/NotificationServiceDockerfile ."
                sh "docker build -t ${DOCKERHUB_USER}/frontend -f DockerFiles/FrontendDockerfile ."
            }
        }
        */
        stage('Login to Docker Hub') {
            steps {
                echo 'Login to Docker Hub'
                withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    sh "docker login -u ${DOCKERHUB_USER} -p ${DOCKERHUB_PASS}"
                }
            }
        }
        /*
        stage('Push Images to Docker Hub') {
            steps {
                echo 'Pushing Images to Docker Hub'
                sh "docker push ${DOCKERHUB_USER}/eurekaregistry"
                sh "docker push ${DOCKERHUB_USER}/apigateway"
                sh "docker push ${DOCKERHUB_USER}/userservice"
                sh "docker push ${DOCKERHUB_USER}/accountservice"
                sh "docker push ${DOCKERHUB_USER}/notificationservice"
                sh "docker push ${DOCKERHUB_USER}/frontend"
            }
        }
        
        stage('Clean Up Local Images') {
            steps {
                echo 'Cleaning Up Local Docker Images'
                sh "docker rmi ${DOCKERHUB_USER}/eurekaregistry"
                sh "docker rmi ${DOCKERHUB_USER}/apigateway"
                sh "docker rmi ${DOCKERHUB_USER}/userservice"
                sh "docker rmi ${DOCKERHUB_USER}/accountservice"
                sh "docker rmi ${DOCKERHUB_USER}/notificationservice"
                sh "docker rmi ${DOCKERHUB_USER}/frontend"
            }
        }
        

        stage('Run Docker Compose') {
            steps {
                echo 'Running Docker Compose to start all containers'
                withEnv(['LC_ALL=en_IN.UTF-8', 'LANG=en_US.UTF-8', 'DOCKER_NAMESPACE=kb1110']) {
                    sh 'docker-compose up -d'
                }
            }
        }
        */
        stage('Deploy with Ansible') {
            steps {
                script {
                    // Install Ansible community.docker collection
                    sh 'ansible-galaxy collection install community.docker'

                    // Run the playbook
                    sh 'ansible-playbook -i inventory_Kuldip playbook.yml'
                }
            }
        }
    }
}
