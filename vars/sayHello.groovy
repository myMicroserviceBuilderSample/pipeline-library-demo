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



