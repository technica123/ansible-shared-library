def call(Map config = [:]) {

    pipeline {

        agent any

        stages {

            stage('Clone') {
                steps {
                    echo "Cloning Repository"

                    checkout scm
                }
            }

        }

    }
}
