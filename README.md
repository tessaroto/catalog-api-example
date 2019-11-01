# Transaction Account Services

Service responsible for managing user transactions, allowing deposits, withdrawals and get current balance.

### Database
As database was used H2, the entity is configured to allow only insertions, ensuring that the data is immutable by the application.

## Features
### Deposit/Withdraw
For insert a new monetary transaction, money in or out, for a given user.

**POST /account/{userId}/transaction**

##### Route Parameters
|  Name | Description  |
| ------------ | ------------ |
|  userId |  User identifier to whom it will be deposited or withdrawn the money |

#### Payload
```json
{
  "amount": 0,
  "operation": "WITHDRAW||DEPOSIT"
}
```

##### Amount 
- To avoid mistakes of values, the **amount** allow only values up to 2 decimal digits. If you send more than 2 decimal digits, you will get http 400.
- Only allow positive values. If you send negative values, you will get http 400.

##### Operation Type
|  Operation Type | Description  |
| ------------ | ------------ |
|  WITHDRAW |  For insert a monetary transaction of withdraw. |
|  DEPOSIT |  For insert a monetary transaction of deposit. |


#### Response
HTTP Status Code: 200, 400.
```json 
{
  "userId": 1,
  "currentBalance ": 10.12
}```

### Current Balance
For return a user's current balance.

**GET /account/{userId}**

##### Route Parameters
|  Name | Description  |
| ------------ | ------------ |
|  userId |  The user identifier that will get the current balance |

#### Response

HTTP Status Code: 200, 404.
```json 
{
  "userId": 1,
  "currentBalance ": 10.12
}```


> If it has not information about the user, will respond 404.

### Errors
Whenever an error occurs the following response format.

```json 
{
  "timestamp": "2019-08-18T16:29:26.745+0000",
  "status": 400,
  "errors": [
    "numeric value out of bounds (<6 digits>.<2 digits> expected)"
  ]
}```

## Development
### Requirements
- Minimum java version 1.8.
- Redis
- Docker CE is optional.


### Building
```sh
./mvnw clean install
```
### Starting application
```sh
redis-server
./mvnw spring-boot:run
```
The application will listening on 8080, if you prefer you can use the Swagger.
http://localhost:8080/swagger-ui.html

### Run tests
```sh
./mvnw test
```
### Docker
if you prefer you can use the Docker for running the application.

#### Docker Compose
In project directory, execute the command to start the stack.
```sh
docker-compose up
```
> Two containers will be started, one for web application and another for Redis.

### Concurrency issues
To solve the concurrency problem, a Redis was used, allowing you to scale the number of VMs / Containers smoothly. 
Follows a sequence diagram to illustrate how it works.

                    
![Example ](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=Q2xpZW50LT5BUEk6IFRyeSBkbyBkZXBvc2l0IG9mIDEwMC4wMCB0byB1c2VyIDEKQVBJLT5SZWRpczogR2V0IGtleSBBY2NvdW50OjEKYWx0IGEACAYgdW5sb2NrZWQKICAgIAAtBS0AYAdSZXR1cm5zIG51bGwAGQVub3RlIG92ZXIgQVBJLABXBwCBBgd0aGUgbG9jawBCBQByDFMAbhAsIHZhbHVlPXsAcgZJZH0AQxpDb25maXJtIGlmAFgFIHdhcyBtYWRlIGZvciBzYW1lIHRyYW5zYWN0aW9uAGoRR2V0IGFnYWluAIEZBWtleSAiAIF7CSIAgVYaAH0cOiBDb21wYXIAgWcGZXJJZCBnZW5lcmF0ZWQgd2l0aACCPQYAFQkAglEFYWx0IGlmIGl0J3MAgiMFc2FtZQCCagUAgiMJRGF0YWJhc2U6IENyZWF0ZSBhIG5ldwCBTBEgICAgACMIAIMeCFNhdmVkIHN1Y2Nlc3NmdWxseQBNDgCDfgdSZW1vdmUAgx4JOiBEZWxldAALBgCCBhQAg30RACoGAE8bLT4AhR8GOiBSZXNwb25kcyAyMDAAhFIFZWxzZSBpZiBub3QAgWsWACkTNDAwIC0gVGhpcwCFIgkAglAIZACDegVhbm90aGVyAINxEWVuZCAgICAKAHAFAIVeCACFTBhyAIVfBQCFNQllZElkAEAZAF1MZW5kCgoKCgpWZXJpZmljYSBzZSBhIGNvbnRhIGVzdGEgYmxvcXVlYWRhCgoKCg&s=modern-blue "Example ")

### TODO
1. Implement a cache of current balance.
2. Implement the authorization for security. s2
3. increase the ability of user ids, changing  the userid to UUID or another type that allow more ids.
