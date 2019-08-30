# gcp-serverless-springboot-datastore
Microservices using Goolge Cloud and Spring Boot with datastore

# Please follow below steps to deploy spring boot aplication into kubernetes

# Build the container image
export PROJECT_ID=[PROJECT_ID]
docker build -t gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v1 .

# Upload the container image
gcloud auth configure-docker
docker push gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v1

# Run your container locally
docker run --rm -p 8080:8080 gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v1