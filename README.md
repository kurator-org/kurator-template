Kurator-Template
==================

Use this project as a template for developing new workflows.

Prerequisites
-------------

Clone the following projects from GitHub:

    git clone https://github.com/kurator-org/kurator-akka.git
    git clone https://github.com/kurator-org/kurator-validation.git
    git clone https://github.com/kurator-org/kurator-template

Build kurator-akka with maven:

    cd kurator-akka
    mvn install

Build kurator-validation:

    cd kurator-validation
    mvn install

Set the JYTHONHOME and JYTHONPATH environment variables required by python workflows:

    set JYTHONHOME=/home/user/jython2.7.1b3
    set JYTHONPATH=/home/user/kurator-template/packages

Maven Build
-----------

Build the template project and example actors with maven:

    cd kurator-template
    mvn install

Running examples
----------------

Maven should produce a jar-with-dependencies artifact (target/kurator-template-1.0-SNAPSHOT.jar). Run this on the command line from the root of the kurator-template project directory:

The command-line workflow runner takes the name of a workflow configuration file and parameters to the workflow as arguments to the -f and -p options.

Run the simple java workflow example via the following:

    java -jar kurator-template-1.0-SNAPSHOT.jar -f src/main/resources/org/kurator/validation/workflows/java_template.yaml -p out=test.csv

Run the simple python workflow example via:

    java -jar kurator-template-1.0-SNAPSHOT.jar -f packages/kurator_template/workflows/python_template.yaml -p in=packages/kurator_template/data/test_mcz.txt