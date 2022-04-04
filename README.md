# cims
Car Inventory Management and Sales

FRONT OFFICE USER
=================
* Add new order
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X POST -d "{\"id\": 0, \"carId\": 1, \"buyerName\": \"Hendra\", \"paymentMethod\": \"CASH\", \"quantity\": 1, \"status\": \"PENDING\"}" http://localhost:8088/cims/api/v1/order?userId=Leo

* Update existing order
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X PUT -d "{\"id\": 1, \"carId\": 1, \"buyerName\": \"Hendra\", \"paymentMethod\": \"CASH\", \"quantity\": 2, \"status\": \"PAID\"}" http://localhost:8088/cims/api/v1/order?userId=Leo

* Delete existing order
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X DELETE http://localhost:8088/cims/api/v1/order/1?userId=Leo

* List all orders
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" http://localhost:8088/cims/api/v1/order

* List particular inventory
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" http://localhost:8088/cims/api/v1/order/1

ADMIN
=====
* Add new inventory
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X POST -d "{\"id\": 0,\"car\": {\"id\": 0,\"make\": \"HONDA\",\"model\": \"Accord 3.4 Asia\",\"fuelType\": \"PETROL\",\"transmission\": \"MANUAL\",\"bodyType\": \"LUXURY_SEDAN\",\"color\": \"GREEN\",\"seat\": \"SEAT_5\"},\"price\": 34800,\"availableStock\": 2,\"createdBy\": \"SYSTEM\",\"createdDate\": null,\"updatedBy\": null,\"updatedDate\": null}" http://localhost:8088/cims/api/v1/inventory?userId=Leo

* Update existing inventory
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X PUT -d "{\"id\": 2,\"car\": {\"id\": 2,\"make\": \"SUZUKI\",\"model\": \"Swift Sport Mild Hybrid\",\"fuelType\": \"PETROL_ELECTRIC\",\"transmission\": \"AUTOMATIC\",\"bodyType\": \"HATCHBACK\",\"color\": \"WHITE\",\"seat\": \"SEAT_5\"},\"price\": 236900,\"availableStock\": 2,\"createdBy\": null,\"createdDate\": null,\"updatedBy\": null,\"updatedDate\": null}" http://localhost:8088/cims/api/v1/inventory?userId=Leo

* Delete existing inventory
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" -X DELETE http://localhost:8088/cims/api/v1/inventory/10?userId=Leo

* List all inventories
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" http://localhost:8088/cims/api/v1/inventory

* List particular inventory
curl -H "Connection: keep-alive" -H "Accept: */*" -H "Content-Type: application/json" http://localhost:8088/cims/api/v1/inventory/10
