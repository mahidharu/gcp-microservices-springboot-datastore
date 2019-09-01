# gcp-microservices-springboot-datastore
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
	curl http://localhost:8080

# Create a container cluster
	#Configure
	gcloud config set project $PROJECT_ID
	gcloud config set compute/zone [COMPUTE_ENGINE_ZONE]

	# for creating clusters with 2 nodes
	gcloud container clusters create gcp-serverless-springboot-datastore --num-nodes=2
	
	gcloud compute instances list

# Deploy your application
	kubectl create deployment gcp-serverless-springboot-datastore --image=gcr.io/${PROJECT_ID}/hello-app:v1

	kubectl get pods

	# Expose your application to the Internet
	kubectl expose deployment gcp-serverless-springboot-datastore --type=LoadBalancer --port 80 --target-port 8080


	kubectl get service (This will give external ip address of the service)
	Once external ip assgined to LoadBalancer then access url from the browser. See below for the reference

	http://[ip_address]/api/getusers

![gcp-microservices-springboot-datastore Sample API response](https://user-images.githubusercontent.com/43684464/64082758-d54be080-ccd9-11e9-850d-e43343c24596.PNG)

# Scale up your application
	kubectl scale deployment gcp-serverless-springboot-datastore --replicas=3
	
	# To view no of replicas running on the cluster
	kubectl get deployment gcp-serverless-springboot-datastore

# Deploy a new version of your app

	docker build -t gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v2 .

	docker push gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v2

	# Rolling update to the existing deployment with an image update
	kubectl set image deployment/gcp-serverless-springboot-datastore gcp-serverless-springboot-datastore=gcr.io/${PROJECT_ID}/gcp-serverless-springboot-datastore:v2
	
# Cleaning up
	
	# Delete the Service
	kubectl delete service gcp-serverless-springboot-datastore
	
	#Delete the container cluster
	gcloud container clusters delete gcp-serverless-springboot-datastore
