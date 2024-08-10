# e-commerce-microservices-api-gateway-kafka-grafana-prometheus-loki-keyclock

✅ Project Overview:

1. API-Gateway
2. Product-Service
3. Inventory-Service
4. Order-Service
5. Notification-Service

This e-commerce project encompasses the implementation of several crucial components for a robust and scalable microservices architecture:

✏️ API Gateway: Facilitates routing, load balancing, and rate limiting, acting as a single entry point for all client requests.

✏️ Authentication and Authorization: Utilizes Keycloak to manage secure user authentication and role-based access control.
Kafka for Notification Service: Implements Kafka as a message broker for efficient and reliable notification delivery.

✏️ Schema Registry: Manages and enforces data schema compatibility in Kafka, ensuring that messages adhere to defined formats.

✏️ Avro: Uses Avro for data serialization, ensuring efficient and compact data exchange between services, especially with Kafka.

✏️ Inter-Service Communication: Ensures seamless communication between microservices, enabling a cohesive system using RestClient.

✏️ Grafana Stack for Logging and Distributed Tracing: Integrates Grafana, Prometheus, and Loki for monitoring, logging, and tracing, providing visibility into system performance and issues.

✏️ Docker: Utilizes Docker to containerize applications, simplifying deployment and scaling across different environments.

This combination of tools and techniques creates a scalable, secure, and observable microservices environment. Docker ensures consistent and efficient management of application containers, while Schema Registry and Avro enhance data integrity and serialization across Kafka streams.


