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

            stage('User Approval') {
                steps {

                    input(
                        message: 'Approve deployment?',
                        ok: 'Proceed'
                    )

                }
            }

            stage('Playbook Execution') {
                steps {

                    echo "Running Ansible Playbook"

                    dir('env/prod') {

                        sh '''
                            ansible-playbook \
                            -i inventory \
                            playbook.yml
                        '''

                    }

                }
            }

            stage('Notification') {
                steps {

                    echo "Sending Notification"

                    echo "Deployment completed successfully"

                }
            }

        }

    }
}
