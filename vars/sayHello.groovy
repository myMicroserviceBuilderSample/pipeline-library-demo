#!/usr/bin/env groovy

def call(String name = 'human') {
  echo "Hello, ${name}."
}

// vars/timedCommand.groovy
def setCommand(commandToRun) {
    cmd = commandToRun
}

def getCommand() {
    cmd
}

def runCommand() {
   timestamps {
      cmdOut = sh (script:"${cmd}", returnStdout:true).trim()
   }
}

def getOutput() {
   cmdOut
}

def call(body) {
    // collect assignments passed in into our mapping
    def settings = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = settings
    body()

    // now, time the commands
   timestamps {
      cmdOutput = echo sh (script:"${settings.cmd}", returnStdout:true).trim()     
   }
   echo cmdOutput
   println "the logfile is in: ${settings.logFilePath}"
}


