#
# Makefile to manage the containers.
# Author: Vladislav Zhuravskiy <hexhoc@gmail.com>
#
.DEFAULT_GOAL:=help
IMAGE_PREFIX=social-media
REPOSITORY=hexhoc
DOCKER_NETWORK=social

# show some help
help:
	@echo ''
	@echo '  Usage:'
	@echo '    make <target>'
	@echo ''
	@echo 'Common Targets:'
	@echo '	create-network		Create the Docker network for the containers'
	@echo '	build		Build gradle projects'
	@echo '	build-images		Build all images from Dockerfiles'
	@echo '	up			Start all containers needed to run the system'
	@echo '	stop			Stop all containers'
	@echo '	restart			Stop + run'
	@echo '	status			Retrieve the status of the containers'
	@echo ''
	@echo 'Clean Targets:'
	@echo '	clean-network		Delete the Docker network'
	@echo '	clean-images		Deletes the created containers and images'
	@echo '	clean-orphan-images	Removes orphan images'
	@echo ''

create-network:
	docker network create ${DOCKER_NETWORK}

build:
	cd backend/gateway-service && sh mvnw clean compile
	cd backend/social-media && sh mvnw clean compile

up:
	cd docker-compose/social-media && docker compose -p ${IMAGE_PREFIX} up -d --build

up-third-party:
	cd docker-compose/social-media-third-party && docker compose up -d --build

stop:
	cd docker-compose && docker compose -p ${IMAGE_PREFIX} stop

restart: stop run

status:
	cd docker-compose && docker compose -p ${IMAGE_PREFIX} ps

build-images:
	cd backend/gateway-service && docker build -t ${REPOSITORY}/social-media-gateway-service .
	cd backend/social-media && docker build -t ${REPOSITORY}/social-media-main-service .
	cd frontend && docker build -t ${REPOSITORY}/social-media-frontend .

clean-images:
	cd docker-compose && docker compose -p ${IMAGE_PREFIX} down --rmi local

clean-orphan-images:
	@docker rmi $(docker images --quiet --filter "dangling=true")

clean-network:
	@docker network rm ${DOCKER_NETWORK}