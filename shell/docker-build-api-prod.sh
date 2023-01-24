cd ..

## build jar

## container
docker stop pullout-api-service-container
docker rm pullout-api-service-container

# image
docker rmi pullout-api-service

# build
docker build -f Dockerfile-api . -t pullout-api-service
docker run -d -p 8080:8080 --name pullout-api-service-container pullout-api-service