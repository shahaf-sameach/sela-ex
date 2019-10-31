# sela-ex

this is a simple toy example of consuming data (hotel prices) from multiple unreliable vendors

# structre & logic
the system consists of those 2 spring boot (reactive web) modules:

### Hotel Vendor
a toy application to return random prices (ranged 0 - 1000) as a response to 
* `GET` request to `/api/v1/hotel/price?hotelName=<some_name>`

a simple delay of random ranged 0 - 1000 millis was introduce to simualte slow requests

>currently the system return a random number with no effect by `hotelName` (this is only a mock service)

### Hotel Service
the main application, act as simple api aggrigator, provide the following api: 
* `GET` request to `/api/v1/hotel/best?hotelName=<some_name>`

upon receiving a request, it issue requests to all registerd vendors (registered via `yml` properites file)

the service uses a timeout to filter out slow clients and to bound the return time of the response, this is configurable via the proprties file
it aggregate all the data and return the best offer

if it couldn't find any offer from the providers in time, it returns `401 not found` response


## run

the system can be deployed by either using **docker-compose** or by running the web server **directly**

### docker-compose

within the root directory, simply run `docker-compose up`
the docker will bind to local port 8080

> keep in mind the first run will take some time, and the first request will return `401 not found`

### direct
1. within the **hotel-service** folder, run `mvn spring-boot:run -Dspring-boot.run.profiles=development`
the web server will bind to local port `8080`
3. within the **hotel-vendor** folder, run `mvn spring-boot:run -Dspring-boot.run.profiles=development`
the web server will bind to local port `8081`


to test both deployments, make a `GET` request to `http://localhost:8080/api/v1/hotel/best?hotelName=<some_name>`

