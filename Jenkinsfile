pipeline {
    environment{
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
        stage('Maven Build RegistryService'){
            steps{
                echo 'Building Job'
                sh 'cd ServiceRegistry; mvn clean install';
                sh 'mv -f ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar JarFiles/';
            }
        }
        stage('Maven Build APIGateway'){
            steps{
                echo 'Building Job'
                sh 'cd ApiGateway; mvn clean install';
                sh 'mv -f ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar JarFiles/'
            }
        }
        stage('Maven Build UserService'){
            steps{
                echo 'Building Job'
                sh 'cd UserService; mvn clean install -DSPRING_DATASOURCE_USERNAME=$MYSQL_CREDENTIALS_USR -DSPRING_DATASOURCE_PASSWORD=$MYSQL_CREDENTIALS_PSW';
                sh 'mv -f UserService/target/UserService-0.0.1-SNAPSHOT.jar JarFiles/';
            }
        }
        stage('Maven Build AccountService'){
            steps{
                echo 'Building Job'
                sh 'cd AccountService; mvn clean install -DSPRING_DATASOURCE_USERNAME=$MYSQL_CREDENTIALS_USR -DSPRING_DATASOURCE_PASSWORD=$MYSQL_CREDENTIALS_PSW';
                sh 'mv -f AccountService/target/AccountService-0.0.1-SNAPSHOT.jar JarFiles/';
            }
        }
        stage('Maven Build NotificationService'){
            steps{
                echo 'Building Job'
                sh 'cd NotificationService; mvn clean install -DSPRING_DATASOURCE_USERNAME=$MYSQL_CREDENTIALS_USR -DSPRING_DATASOURCE_PASSWORD=$MYSQL_CREDENTIALS_PSW';
                sh 'mv -f NotificationService/target/NotificationService-0.0.1-SNAPSHOT.jar JarFiles/';
            }
        }
        
        stage('Build Image for Microservices'){
            steps{
                echo 'Building docker Image'
                sh "docker build -t $DOCKERHUB_USER/eurekaregistry -f DockerFiles/ServiceRegistryDockerfile .";
                sh "docker build -t $DOCKERHUB_USER/apigateway -f DockerFiles/APIGatewayServiceDockerfile .";
                sh "docker build -t $DOCKERHUB_USER/userservice -f DockerFiles/UserServiceDockerfile .";
                sh "docker build -t $DOCKERHUB_USER/accountservice -f DockerFiles/AccountServiceDockerfile .";
                sh "docker build -t $DOCKERHUB_USER/notificationservice -f DockerFiles/NotificationServiceDockerfile .";
                sh "docker build -t $DOCKERHUB_USER/frontend -f DockerFiles/FrontendDockerfile .";
            }
        }
    
        stage('Login into docker hub'){
            steps{
                echo 'Login into docker hub'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin';
            }
        }
        stage('Push Image to DockerHub'){
            steps{
                echo 'Pushing Images into DockerHub'
                sh 'docker push $DOCKERHUB_USER/eurekaregistry';
                sh 'docker push $DOCKERHUB_USER/apigateway';
                sh 'docker push $DOCKERHUB_USER/userservice';
    	        sh 'docker push $DOCKERHUB_USER/accountservice';
                sh 'docker push $DOCKERHUB_USER/notificationservice';
                sh 'docker push $DOCKERHUB_USER/frontend';
            }
        }
        stage('Delete Image from localsystem'){
            steps{
                echo 'Deleting Docker Image in docker'
                sh 'docker rmi $DOCKERHUB_USER/eurekaregistry';
                sh 'docker rmi $DOCKERHUB_USER/apigateway';
                sh 'docker rmi $DOCKERHUB_USER/userservice';
                sh 'docker rmi $DOCKERHUB_USER/accountservice';
                sh 'docker rmi $DOCKERHUB_USER/notificationservice';
                sh 'docker rmi $DOCKERHUB_USER/frontend';

            }
        }
        stage('Run ansible playbook'){
            steps{
                echo 'Running the ansible playbook yml file'
                sh 'export LC_ALL=en_IN.UTF-8;export LANG=en_US.UTF-8;ansible-playbook -i inventory_shashi playbook.yml'
            }
        }
       
    }
}
