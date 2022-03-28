@Library('shared-library@stable') _

def pipelineConfig = [
    "stackName": "protocol-tezos",
    "services": [
        [name: 'api', path: './api']
        [name: 'listener', path: './listener']
    ]
]

serviceCI(pipelineConfig)
