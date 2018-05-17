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


@Grab('org.apache.commons:commons-lang3:3.4+')
import org.apache.commons.lang.time.StopWatch

def call(String cmdToRun) {
      def sw = new StopWatch()
      def proc = "$cmdToRun".execute()
      sw.start()
      proc.waitFor()
      sw.stop()
      println("The process took ${(sw.getTime()/1000).toString()} seconds.\n")
}
