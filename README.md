# ADAPTIVE-SHADOW

<p align="center">
<img src="frontend/readme/logo.png" width="350px" height="250px"/>
</p>

<p align="center">
  <img src="https://img.shields.io/npm/v/@hilla/frontend.svg" alt="Latest Stable Version">
  <a href="https://github.com/vaadin/hilla/releases"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fdev%2Fhilla%2Fhilla%2Fmaven-metadata.xml" alt="Releases"></a>  
  <img src="https://img.shields.io/badge/build-passing-brightgreen" alt="Build Status">
  <img src="https://img.shields.io/badge/version-1.0-green" alt="Version">
</p>

ADAPTIVE SHADOW is made available through a RESTful web application that allows users to:
1 Instantiate a Domain Model for IoT thus generating Digital Shadows of IoT components;
2 Model a BPMN scenario leveraging Digital Shadows for IoT components;
3 Simulate the BPMN model and obtain feedback and insights for the process improvement.

# Table of contents
<!--ts-->
   * [Functionalities](#functionalities)
   * [How to run](#how-to-run)
   * [Domain Model](#domain-model)
   * [References](#References)
   * [License](#License)
<!--te-->

# Functionalities

<p align="center">
<img src="Domain Model/img/architecture.png" width="400px" height="200px"/>
</p>

ADAPTIVE SHADOW is a web application that aims to model and optimize IoT-Enhanced Business Processes continuously. 
The implementation of the framework consists of two parts: (i) instantiating Digital Shadows for digitizing IoT entities using the IoT MERODE Domain Model, and (ii) the BPM infrastructure for the optimization of IoT-Enhanced Business Processes which integrate Digital Shadows of IoT entities. 

# (i) The Domain Model


# (ii) The Business Process Management Infrastructure


<p align="center">
<img src="Domain Model/img//bpmprocess.png"/>
</p>

# How to run

## Prerequisites

- [Node](https://nodejs.org/en) 16.14 or later;
- [JDK](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html) 19 or later (Be sure that you have set the JAVA_HOME environment variable pointing to version 19 of the Java JDK);
- [Python](https://www.python.org/downloads/) 3.9.X or higher.

Run the following command to install dependencies:
```bash
yarn install
```

There are several ways to run ADAPTIVE SHADOW:

1. Clone this repository and run the tool locally by executing the following command:

Windows:
```bash
.\mvnw
```
macOS / Linux:
```bash
./mvnw
```

Once launched, the application will be available at [http://localhost:8080](http://localhost:8080).

2. Import the project into an IDE and run the class "Application.java"

```bash
 ADAPTIVE-SHADOW\src\main\java\com\example\application\Application.java
```

# Domain Model Specification

To represent the IoT system's components, two well-known and generally accepted ontologies, the Semantic Sensor Network (SNN) and Sensor, Observation, Sample, and Actuator (SOSA) were mapped into a MERODE Domain Model for IoT [1]. 
The domain model allows for the instantiation of IoT system entities by generating Digital Shadows that synchronize with their physical counterparts' data, state, and characteristics in real-time. 

MERODE is a model-driven engineering approach that -besides a focus on model quality- includes an incremental development strategy that ensures a well-structured and scalable system that represents an effective base for capturing domain-specific knowledge and establishing effective information systems. This approach relies on the concept of Model-Driven Engineering (MDE) placing a strong focus on ensuring the quality of the data produced and managed by a standard model mapped from the Semantic Sensor Network (SSN) and SOSA Ontology.

## Graphs

<p align="center">
<img src="frontend/readme/model.png" width="600px" height="300px"/>
</p>

## Attributes

| UML Class | Attribute Name | Type | Description |
| --- | --- | --- | --- |
| **Platform** | Name | `STRING` | Name of the Platform |
|  | Description | `LONG TEXT` | Description of the Platform |
|  | hostedBy | `LONG TEXT` | Entity on which the Platform is hosted |
| **FeatureOfInterest** | Name | `STRING` | Name of the Feature of Interest |
|  | Description | `LONG TEXT` | Description of the Feature of Interest |
| **Device** | Name | `STRING` | Name of the Feature of Interest |
|  | Description | `LONG TEXT` | Description of the Feature of Interest |
|  | Status | `BOOLEAN` | Value of the Device Status. 1 represent ON, while 0 OFF  |
| **RegisteredDevice** | deviceName | `STRING` | Name of the Device registered |
|  | platformName | `LONG TEXT` | Name of the Platform registered  |
|  | startTime | `DATE/TIME` | Device registration time stamp |
| **RegisteredPlatform** | platformName | `STRING` | Name of the Platform registered |
|  | featureofinterestName | `LONG TEXT` | Name of the Feature of Interest registered  |
|  | startTime | `DATE/TIME` | Platform registration time stamp |
| **Property** | featureofinterestName | `LONG TEXT` | Name of the Feature of Interest registered  |
|  | description | `LONG TEXT` | Description of the Property to be measured from the Feature of Interest |
| **Procedure** |  deviceName | `STRING` | Name of the Device registered |
|  | description | `LONG TEXT` | Description of the device's Procedure to perform a measurement |
| **DeviceUsage** | usageType | `STRING` | Name of the Platform |
|  | startTime | `DATE/TIME` | Device usage time stamp start |
|  | endTime | `DATE/TIME` | Device usage time stamp end |
| **DeviceResult** | value | `FLOAT` | Measured value |
| | unit | `STRING` | Measured value unit |
| | producedBy | `STRING` | Device Reference |
| | startTime | `DATE/TIME` | Measurement time stamp start |
|  | endTime | `DATE/TIME` | Measurement time stamp end |

The MERODE Domain Model can be processed by the MERLIN Code Generator, which provides the option to generate two different types of code: a Java prototype of the application or a RESTful web application. Both solutions adhere to the logic defined in the domain model and are included in the project folder.

## The Java Prototype

The Java prototype encompasses a straightforward Graphical User Interface (GUI) displaying a list and details of the objects defined in the domain model. It also offers buttons for creating, modifying, and terminating object instances.

The Domain Model can be tested in the GUI offered by MERODE, by directly executing the RUN file at the following path of the project:

```bash
ADAPTIVE-SHADOW\Domain Model\DS_GUI_Prototype\RUN 
```

<p align="center">
  <img src="Domain Model/img/merodeGUI.png" width="400px" height="280px"/>
  <img src="Domain Model/img/instanceExample.png" width="400px" height="280px"/>
</p>

## The RESTful Web Application for using the Domain Model

The RESTful web application consists of a MAVEN project, offering a range of API services for each business event. The Domain Layer defines several business object types (EDG), each of which has a corresponding set of business event types specified in the Service Layer (OET). These business event types, which encompass actions such as creation, modification, termination, and more, can be performed through API services.

ADAPTIVE SHADOW directly implements the APIs necessary to instantiate the Domain Model in the web application of this project.

# References

[1] Compagnucci, I., Serral, E., Snoeck,M. (2023). Supporting Digital Twins Systems Integrating the MERODE Approach. International Conference on Model Driven Engineering Languages and Systems Companion (MODELS-C),  pp. 449-458. ACM/IEEE.

# License

ADAPTIVE SHADOW is under the [MIT](https://github.com/ADAPTIVE-SHADOW/blob/master/LICENSE) license.
