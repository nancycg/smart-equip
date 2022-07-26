# smart-equip
To make sure our application is accessed by humans and not a by any computers or bot.

### What is this repository for? ###

* The smart-equip project is to verify input by a human only and not by a machine/bot.
* Ver0.0.1

### set up instructions ###

* Configuration: You can change the port in application.yaml file using server:port configuration. The default port is 8080.
* Database configuration: None
* How to run tests:While running the application each time test cases executed first
* Deployment instructions: None

### Contribution guidelines ###

* Please write/review test cases for every code changes.
* Code review is compulsory to merge into main


### Steps to run

First step is clone the project to your system and open any IDE:

git clone https://github.com/nancycg/smart-equip.git

I am using intelliJ IDE.

* Go to smartEquip\random-number-sum folder and run the batch file  **RandomRun.bat**. The server get started on default port 8080. Please refer **application.yaml** file to change default PORT
* open POSTMAN for making GET and POST calls
* GET call **localhost:8080/smart-equip/query** to generate random numbers. Sample response as follows...

```json
{
    "id": "2da7135a-45d4-4f40-894f-a45a42e30018",
    "message": "Here you go, solve the question: Please sum the numbers [6, 6, 7]",
    "randomNumbers": [
        6,
        6,
        7
    ],
    "success": false
}
```


* copy the id field for next request. This is important for the success of next request.


* POST call **localhost:8080/smart-equip/verify** with JSON body as below[use copied id here]

```json
{
    "id":"2da7135a-45d4-4f40-894f-a45a42e30018",
    "sum":19,
    "randomNumbers":[6,6,7]
}
```


### SUCCESS response:

```json
{
    "id": "2da7135a-45d4-4f40-894f-a45a42e30018",
    "message": "That's great",
    "randomNumbers": [
        6,
        6,
        7
    ],
    "success": true
}
```

### Running screenshots:

![image](https://user-images.githubusercontent.com/20887138/181005126-9a3e5f6b-6482-4cd6-bead-f645c56910cb.png)

![image](https://user-images.githubusercontent.com/20887138/181005224-ed5f62f0-c53e-4f23-b5cd-73ac9621685b.png)


### Exception Handling:

![image](https://user-images.githubusercontent.com/20887138/181007545-a0bbd795-76b5-494a-8326-9921946862d9.png)


![image](https://user-images.githubusercontent.com/20887138/181007697-b929a835-900a-4fe4-a75a-4e496b5be906.png)


![image](https://user-images.githubusercontent.com/20887138/181007814-8f638366-5f0e-4896-aca4-505e44fa54cd.png)



