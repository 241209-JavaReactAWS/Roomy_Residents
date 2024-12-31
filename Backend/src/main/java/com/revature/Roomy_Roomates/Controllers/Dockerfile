# This file is called a Dockerfile, we'll get to its uses in a minute

# GOAL: Deploy our app via a container to ensure it behaves consistently no matter the deployment environment

# Steps:
# Creating an Image (Blueprint for a container)
#We create an image by using a Dockerfile which outlines the steps needed to create the image and hold the necessary data
#Distributing the image itself (optional)
# Share this image for others to pull via the docker hub
# Running the application from the image in a container
# By using docker/podman we'll use the image to build a container and start our application


FROM amazoncorretto:17

# The next step is to pull in an executable version of the code that is bundeled together
# with its dependencies.

COPY target/app.jar app.jar

# At this point, we've built an executable version of our code (app.jar)
# and are giving it an environment in (amazoncorretto:17)(, now we have a couple more steps
# to get everything working.
# This application

EXPOSE 8080