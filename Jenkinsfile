@Library('shared-library@experiment/gradle-improvements') _

def pipelineConfig = [
    "stackName": "protocol-tezos",
    "services": [
        [name: 'tezos-listener', path: './listener']
    ]
]

serviceCI(pipelineConfig)
