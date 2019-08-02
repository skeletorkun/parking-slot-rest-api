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
* Test the server
```
curl http://127.0.0.1:8080/ping 
```
### Using the Parking Lot Rest Api ###
#### Configure a Parking Lot ####
Create a Vubox in FM. For example in dev you can :
* Connect VPN "GRA"
* Go to [suiperadmin](http://10.4.102.22/suiperadmin)
* Choose menu "Read only", then "Manage vms"
* Click "Select DEV"
* Click on "Vubox" button for line "F0001"
* Click "+ Add vubox" on buttom
* Type Vubox Id
* Set Status as "Active"
* Click "Create"


#### Create a vehicle ####
Create a vehicle in BO with
* the right VIN number:
   * VIN MUST start with "SEG" for segway vehicle
   * VIN MUST start with "NIU" for niu vehicle
* "VWGateway" as wake up provider 
* the created Vubox ID

Click "Confirm vubox", then "Save".


#### Initialize status ####
Call the generic gateway to get a status. This status will be sent to FleetManager and the vehicle will be set as "Connected".

`curl -X POST https://frnce-devjava-api.vulog.com/vwgateway/rest/fleets/F0001/vehicles/{vuboxId}/status`# parking-slot-rest-api
