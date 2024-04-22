# ADAPTIVE-SHADOW

<p align="center">
<img src="frontend/readme/logo.png" width="200px" height="150px"/>
</p>

<p align="center">
  <img src="https://img.shields.io/npm/v/@hilla/frontend.svg" alt="Latest Stable Version">
  <a href="https://github.com/vaadin/hilla/releases"><img src="https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fdev%2Fhilla%2Fhilla%2Fmaven-metadata.xml" alt="Releases"></a>  
  <img src="https://img.shields.io/badge/build-passing-brightgreen" alt="Build Status">
  <img src="https://img.shields.io/badge/version-1.0-green" alt="Version">
</p>

# Table of contents
<!--ts-->
   * [Functionalities](#functionalities)
   * [How to run](#how-to-run)
   * [Domain Model](#domain-model)
<!--te-->

# Functionalities

# How to run

# Domain Model

MERODE is a model-driven engineering approach that -besides a focus on model quality- includes an incremental development strategy that ensures a well-structured and scalable system that represent an effective base for capturing domain-specific knowledge and establishing effective information systems. This approach relies on the concept of Model-Driven Engineering (MDE) placing a strong focus on ensuring the quality of the data produced and managed by a standard model mapped from the Semantic Sensor Network (SSN) and SOSA Ontology.

<p align="center">
<img src="frontend/readme/approach.png" width="400px" height="250px"/>
</p>

<p align="center">
<img src="frontend/readme/model.png" width="600px" height="300px"/>
</p>

<p align="center">
<img src="frontend/readme/tableSpec.png" width="400px" height="130px"/>
</p>

## The Web Application

The RESTful web application consists of a MAVEN project, offering a range of API services for each business event. The Domain Layer defines several business object types (EDG), each of which has a corresponding set of business event types specified in the Service Layer (OET). These business event types, which encompass actions such as creation, modification, termination, and more, can be performed through API services.

<p align="center">
<img src="frontend/readme/Simulation.png" width="600px" height="300px"/>
</p>

## The Java Prototype

The Java prototype encompasses a straightforward Graphical User Interface (GUI) displaying a list and details of the objects defined in the domain model. It also offers buttons for creating, modifying, and terminating object instances.
