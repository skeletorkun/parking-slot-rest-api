# PARKING SLOT REST API#

A microservice for tracking parking records. 

### Prerequisites  ###

* H2 in-memory DB
* Maven 3

### Few commands to start ###

* Launch the unit tests
```
mvn test 
```
* Create the package
```
mvn clean package
```

* Launch the server
```
java -jar target/parking-slot-rest-api-2.0.0.RELEASE.jar
```
* Swagger
```
http://localhost:8090/swagger-ui.html#/parking-lot-controller 
```

### Using the Rest Api ###
To be able to use the api, at least one parking has to be configured with space and billing configuration. 
The api supports creation and ending of a parking 

##### Configure a Parking Lot ####
There is no api support this but `TestDataSetupService` injects mock data.

* Create a parking `Lot` with `Space`s
* Create a `BillingConfig` with a `PricingPolicy` and other parameters

##### Create a parking ####
Use the POST endpoint `/lots/:lotId/parkings` to create a parking record.
Sample post body:

`{
  "vehiclePlate": "123-ABC-XY",
  "vehicleType": "ELECTRIC_20W"
}`

##### End a parking ####
Use the POST endpoint `/lots/:lotId/parkings/:parkingId/end` to end a parking. 
The operation will return the parking that will contain the cost.


### Future work ###
This first version lacks some features that would be helpful such as further api operations such as:
* GET all parking records
* CRUD operations for setting up a Parking `Lot` with `Space`s and a `BillingConfig`

##### Implementing a new Pricing Policy #####
The customers will be charged using different `BillingService` implementations, based on the`BillingConfig` of a `Lot`.
To change the pricing of a lot, it suffices to inject a new `BillingConfig` as a dependency. 
 
