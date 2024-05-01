
# Hi, I'm Darius! 👋

And here you can find the documentation of the Dance Contest Manager project.
## 🚀 About Me
💻Dedicated back-end software developer | 👨‍💻Motivated to work for companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology | 💼 Actively looking for a job |  5 personal projects


## 🛠 Skills
Back-end development, Software development, Web development, Java, Spring framework, Spring boot, Data structures, Algorithms, OOP, MySQL, Relational databases, SQL, Git, HTML, CSS, Web services, Rest APIs, Unit Testing

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/iDariusSerban)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/darius-serban/)


# Dance Contest Manager App

This application is designed for dance competition organizers, 
supporting two main user roles: admins and judges.

Judges have the capability to score participants across various stages (such as qualifiers) and
divisions(like novice) within a competition. The system automatically identifies participants who advance to subsequent stages based on their scores. 

As an admin, you can efficiently check in participants using QR codes generated upon registration.
This streamlined process enhances the management and execution of dance competitions, ensuring a smooth and accurate progression from initial qualifiers to final rounds.

## Features


### As a Judge user, I can:

- Grade a participant 
- Modify that grade


### As an admin, I can:
- Create contest
- Delete contest
- View contest
- Create judge
- Assign judge to a specific division of a contest
- Delete judge
- View  judge
- Create participant and send a mail with a qrCode
- Check in participant
- Delete participant
- View participant
- View all grades
- View grades by participant
- View grades by contest
- View grades by division
- View grades by stage
- Finalize stage and generate report 



## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:

https://in_progress


## API Reference

#### Create a contest

```http
  POST /contest/add
```

| Parameter | Type     | Description                                             |
| :-------- | :------- |:--------------------------------------------------------|
| `body`    | `json`   | **Required**. The properties of the contest to be added |

Request body example:

```json
{
  "name" : "Contest",
  "location" : "Cluj-Napoca"
}
```  

#### Update contest

```http
  PUT contest/update/${contest_id}
```

| Parameter | Type   | Description                                                |
|:----------|:-------|:-----------------------------------------------------------|
| `id`      | `long` | **Required**. The id of the contest to be updated.         | 
| `body`    | `json` | **Required**. The properties of the contest to be updated. | 

Request body example:

```json
{
  "name" : "Jack and Jill",
  "location" : "Cluj-Napoca"
}
```  

#### Delete contest

```http
  DELETE /contest/delete/${contest_id}
```

| Parameter | Type   | Description                                        |
|:----------|:-------|:---------------------------------------------------|
| `id`      | `long` | **Required**. The id of the contest to be deleted. |

 


#### Find all contests

```http
  GET /contest/findAll
```

#### Find contest 

```http
  GET /contest/findById/${contest_id}
```

| Parameter | Type   | Description                                       |
|:----------|:-------|:--------------------------------------------------|
| `id`      | `long` | **Required**. The id of the contest.              |





#### Create judge

```http
  POST /judge/create
```

| Parameter | Type     | Description                                              |
|:----------| :------- |:---------------------------------------------------------|
| `body`    | `json` | **Required**. The properties of the judge to be created. | 

Request body example:
```json
{
  "name" : "Judge1",
  "country" :"Romania"
}
```

#### Assign judge to division 

```http
  PUT /judge/add
```

| Parameter     | Type     | Description                                            |
|:--------------|:---------|:-------------------------------------------------------|
| `body`    | `json` | **Required**. The contest and division to be assigned. | 


Request body example:
```json
{
  "judge_id" : "2",
  "contestName" :"Jack and Jill",
  "divisionType" : "Novice"
}
```

#### View all the judges

```http
  GET /judge/findAll
```


#### View all the judges from a division

```http
  GET /judge/findAllByDivision/${division_id}
```
| Parameter | Type   | Description                           |
|:----------|:-------|:--------------------------------------|
| `id`      | `long` | **Required**. The id of the division. |

#### View all the judges from a contest

```http
  GET /judge/findAllByContest/${contest_id}
```
| Parameter | Type   | Description                          |
|:----------|:-------|:-------------------------------------|
| `id`      | `long` | **Required**. The id of the contest. |


#### Delete judge

```http
  DELETE /judge/delete/${judge_id}
```
| Parameter | Type   | Description                                      |
|:----------|:-------|:-------------------------------------------------|
| `id`      | `long` | **Required**. The id of the judge to be deleted. |


#### Create participant 

```http
  POST /participant/add
```

| Parameter     | Type     | Description                                                    |
|:--------------|:---------|:---------------------------------------------------------------|
| `body`    | `json` | **Required**. The properties of the participant to be created. | 


Request body example:
```json
{
  "name" : "Denisa",
  "country" : "Romania",
  "emailAddress" : "example@gmail.com ",
  "role" : "follower",
  "contestName" : "Jack and Jill",
  "divisionType" : "Advanced"
}
```

#### Check in  participant

```http
  PUT /participant/checkIn/${id}
```

| Parameter     | Type     | Description                                               |
|:--------------|:---------|:----------------------------------------------------------|
| `id`      | `long` | **Required**. The id of the participant to be checked in. |

#### Delete  participant

```http
  DELETE /participant/delete/${participant_id}
```

| Parameter     | Type     | Description                                            |
|:--------------|:---------|:-------------------------------------------------------|
| `id`      | `long` | **Required**. The id of the participant to be deleted. |



## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}
```  

#### Register

```http
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}

```  
After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer 
```  


## Database model
![App Screenshot](https://i.imgur.com/VTQibfA_d.webp?maxwidth=760&fidelity=grand)

## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For building and running the application with Docker, you need:

- Docker Desktop (for Windows and Mac) or Docker Engine (for Linux)
- Docker Compose (optional, for orchestrating multi-container applications)

## Dependencies

You don't need any additional dependencies.
All dependencies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/iDariusSerban/dance-contest-manager
```

Go to the project directory

```bash
  cd my-project
```

## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Running Locally with Docker

### Build the Docker Image

Navigate to the root directory of your project where the Dockerfile is located, and run the following command:

```http
docker build -t dcm-app .
```

### Run the Application

```http
docker run -p 8080:8080 dcm-app
```

### Run using Docker Compose (Optional)

If your application requires running multiple services (like an app server and a database), you can use Docker Compose to manage these services. Here is an example docker-compose.yml file for your application:

```http
docker-compose up
```

This command builds and starts both the application and the database containers. The application will connect to the MySQL database as configured in your application's properties.

## Deployment with Docker
Deploying your dockerized application can vary based on your hosting provider. Typically, you would push your Docker image to a container registry (e.g., Docker Hub, GitHub Container Registry) and then pull and run it on your production server. Here are the basic steps for pushing to Docker Hub:

### Tag your image
```http
docker tag dcm-app yourusername/dcm-app:latest
```

### Push your image to the registry
```http
docker push yourusername/dcm-app:latest
```

After pushing your image, you can follow your hosting provider instructions.


## Usage

You can use the demo version of the app, using SwaggerUI and following this link:

```javascript
https:/willbeuploaded
```

First, obtain an access token by running the /authenticate endpoint with username "user" and password "pass", which will grant you admin rights in the application.

![App Screenshot](https://i.imgur.com/VTQibfA_d.webp?maxwidth=760&fidelity=grand)

After that, authorize yourself by clicking the authorize button and copy-pasting the token from the response.

![App Screenshot](https://i.imgur.com/arTX2Ke_d.webp?maxwidth=760&fidelity=grand)

From now on, you can use all other available endpoints, as per swagger documentation.
## Roadmap

In the future, application can be extended with following:

- 

## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)