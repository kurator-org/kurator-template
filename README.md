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

Project directory structure
---------------------------

The following gives an overview of the directory structure of this template project:

- packages/kurator_template (each subdirectory of packages is a python package containing actors)
    - workflows (contains workflow definition files as yaml)
        - python_template.yaml
    - actor_template.py (python actor implementation class that can be used as a template)
- src/main/java
    - org/kuration/validation/actors
        - JavaTemplateActor.java (template to create your own Java actor)
- src/main/resources
    - org/kuration/validation
        - workflows/java_template.yaml (workflow definition files for Java actors)
        - actors.yaml (actor definitions for import by workflow config)
        - types.yaml (type definitions for the yaml configuration and actors)

Template actors and workflows
-----------------------------

The following link to the kurator-akka project describes the basic concepts behind a Java and Python actor implementation as well as the workflow yaml format. This section also gives an overview of the actors and workflows in this project.

https://github.com/kurator-org/kurator-akka#example-actor-and-workflow

### Java actor ###

The JavaTemplateActor class can be found at src/main/java/org/kurator/validation/actors/JavaTemplateActor.java

This class contains 4 lifecycle methods listed below:

* onInitialize() - invoked after the constructor, override this method to perform initialization tasks before the actor is started
* onStart() - can be overridden by children classes to perform any tasks that must occur once at the beginning of a workflow run but after all actors have been initialized.
* onData() - handles data message, this method is likely to contain most of your actor business logic
* onEnd() - called when the actor has stopped sending messages but just before it stops fully

Additionally, public fields of a KuratorActor can be configured with default values or as workflow parameters in the yaml. The JavaTemplateActor class has a single public field as an example.

### Java actor yaml and workflow ###

The actors.yaml file found in src/main/resources/org/kurator/validation contains global definitions of actors as blocks of yaml.

    - id: JavaTemplateActor
      type: Actor
      properties:
        actorClass: org.kurator.validation.actors.JavaTemplateActor

The id in this configuration is used when referencing the actor in a workflow. Types are defined in the types.yaml file in the same directory (in this project the file is empty and references the file in kurator-akka instead). The javaClass property takes the fully qualified class name of the KuratorActor subclass.

Other actors from dependency projects can also be defined here as well (the reader and writer)

An example workflow that uses an instance of the template actor can be found in src/main/resources/org/kurator/validation/workflows/java_template.yaml. The first three blocks of yaml describe the actors to be used in this workflow and their config. The type of each of these definitions maps to a type in actors.yaml. The parameters property maps a key (variable name) to a value. The "input" parameter in the yaml is the same public field mentioned earlier in JavaTemplateActor.