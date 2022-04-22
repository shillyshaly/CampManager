FROM shillyshaly/test:campManager
ENV MYSQL_ROOT_PASSWORD team4
ENV MYSQL_DATABASE Camp_Manager
COPY ./src/startUp.sql /docker-entrypoint-initdb.d/
